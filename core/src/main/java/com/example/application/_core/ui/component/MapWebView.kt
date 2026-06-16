package com.example.application._core.ui.component

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.application._core.data.maps.model.MapLocation
import kotlinx.serialization.json.Json

private const val TAG = "MapWebView"
private const val HOSTED_MAP_URL = "https://semarin.online/anterin/embed"

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MapWebView(
    onMapClick: (Double, Double) -> Unit,
    userLocation: Pair<Double, Double>? = null,
    pickupLocation: MapLocation? = null,
    destinationLocation: MapLocation? = null,
    routeGeoJson: String? = null,
    isInteractionEnabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    var isMapReady by remember { mutableStateOf(false) }
    var webViewRef by remember { mutableStateOf<WebView?>(null) }

    // Handle User Location Camera movement
    LaunchedEffect(userLocation, isMapReady) {
        if (isMapReady && userLocation != null) {
            webViewRef?.evaluateJavascript(
                "setUserLocation(${userLocation.first}, ${userLocation.second})",
                null
            )
        }
    }

    // Handle Markers with proper JSON escaping
    LaunchedEffect(pickupLocation, destinationLocation, isMapReady) {
        if (isMapReady) {
            try {
                val pJson = pickupLocation?.let {
                    Json.encodeToString(it)
                } ?: "null"

                val dJson = destinationLocation?.let {
                    Json.encodeToString(it)
                } ?: "null"

                // Use JSON.stringify on web side for safe parsing
                webViewRef?.evaluateJavascript(
                    "setMarkers($pJson, $dJson)",
                    null
                )
                Log.d(TAG, "Markers updated - Pickup: ${pickupLocation?.address ?: "null"}, Destination: ${destinationLocation?.address ?: "null"}")
            } catch (e: Exception) {
                Log.e(TAG, "Error updating markers: ${e.message}", e)
            }
        }
    }

    // Handle Routing with proper JSON handling
    LaunchedEffect(routeGeoJson, isMapReady) {
        if (isMapReady) {
            try {
                if (routeGeoJson != null) {
                    // GeoJSON is already a valid JSON string, pass it directly
                    val safeGeoJson = routeGeoJson.replace("\"", "\\\"")
                    webViewRef?.evaluateJavascript(
                        "drawRoute(\"$safeGeoJson\")",
                        null
                    )
                    Log.d(TAG, "Route rendered")
                } else {
                    webViewRef?.evaluateJavascript("clearRoute()", null)
                    Log.d(TAG, "Route cleared")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error updating route: ${e.message}", e)
            }
        }
    }

    // Handle Interaction Lock
    LaunchedEffect(isInteractionEnabled, isMapReady) {
        if (isMapReady) {
            try {
                webViewRef?.evaluateJavascript(
                    "setInteractionEnabled($isInteractionEnabled)",
                    null
                )
                Log.d(TAG, "Interaction enabled: $isInteractionEnabled")
            } catch (e: Exception) {
                Log.e(TAG, "Error setting interaction state: ${e.message}", e)
            }
        }
    }

    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            WebView(ctx).apply {
                webViewRef = this
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                setLayerType(WebView.LAYER_TYPE_SOFTWARE, null)
                setBackgroundColor(android.graphics.Color.TRANSPARENT)

                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    @Suppress("DEPRECATION")
                    databaseEnabled = true
                    allowFileAccess = true
                    allowContentAccess = true
                    loadWithOverviewMode = true
                    useWideViewPort = true
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

                    cacheMode = WebSettings.LOAD_NO_CACHE

                    // Production WebView Settings for Stability
                    userAgentString = userAgentString?.plus(" SemarinAnterin/1.0")
                    javaScriptCanOpenWindowsAutomatically = false
                    defaultTextEncodingName = "utf-8"

                    // Performance & Security
                    blockNetworkImage = false
                    blockNetworkLoads = false
                }

//                webViewClient = object : WebViewClient() {
//                    override fun onPageFinished(view: WebView?, url: String?) {
//                        super.onPageFinished(view, url)
//                        isMapReady = true
//                        Log.d(TAG, "Map page loaded successfully from: $url")
//                    }
//
//                    override fun onReceivedError(
//                        view: WebView?,
//                        request: android.webkit.WebResourceRequest?,
//                        error: android.webkit.WebResourceError?
//                    ) {
//                        super.onReceivedError(view, request, error)
//                        Log.e(
//                            TAG,
//                            "WebView Error - URL: ${request?.url}, Error Code: ${error?.errorCode}, Description: ${error?.description}"
//                        )
//                    }
//                }

                webViewClient = object : WebViewClient() {

                    override fun onPageStarted(
                        view: WebView?,
                        url: String?,
                        favicon: android.graphics.Bitmap?
                    ) {
                        super.onPageStarted(view, url, favicon)
                        Log.d(TAG, "PAGE STARTED: $url")
                    }

                    override fun onPageFinished(
                        view: WebView?,
                        url: String?
                    ) {
                        super.onPageFinished(view, url)
                        isMapReady = true
                        Log.d(TAG, "PAGE FINISHED: $url")
                    }

                    override fun onReceivedError(
                        view: WebView?,
                        request: android.webkit.WebResourceRequest?,
                        error: android.webkit.WebResourceError?
                    ) {
                        super.onReceivedError(view, request, error)

                        Log.e(
                            TAG,
                            "ERROR ${error?.errorCode} ${error?.description} URL=${request?.url}"
                        )
                    }

                    override fun onReceivedHttpError(
                        view: WebView?,
                        request: android.webkit.WebResourceRequest?,
                        errorResponse: android.webkit.WebResourceResponse?
                    ) {
                        super.onReceivedHttpError(view, request, errorResponse)

                        Log.e(
                            TAG,
                            "HTTP ${errorResponse?.statusCode} URL=${request?.url}"
                        )
                    }

                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: android.webkit.WebResourceRequest?
                    ): Boolean {
                        Log.d(TAG, "NAVIGATION: ${request?.url}")
                        return false
                    }
                }

                webChromeClient = object : WebChromeClient() {
                    override fun onConsoleMessage(consoleMessage: android.webkit.ConsoleMessage?): Boolean {
                        val level = when (consoleMessage?.messageLevel()) {
                            android.webkit.ConsoleMessage.MessageLevel.ERROR -> "ERROR"
                            android.webkit.ConsoleMessage.MessageLevel.WARNING -> "WARNING"
                            android.webkit.ConsoleMessage.MessageLevel.LOG -> "LOG"
                            android.webkit.ConsoleMessage.MessageLevel.TIP -> "TIP"
                            android.webkit.ConsoleMessage.MessageLevel.DEBUG -> "DEBUG"
                            else -> "UNKNOWN"
                        }
                        Log.d(TAG, "JS [$level]: ${consoleMessage?.message()}")
                        return true
                    }
                }

                addJavascriptInterface(object {
                    @JavascriptInterface
                    fun onMapClick(lat: Double, lng: Double) {
                        Log.d(TAG, "Map clicked at: ($lat, $lng)")
                        post { onMapClick(lat, lng) }
                    }
                }, "AndroidInterface")

                clearCache(true)

                val timestampUrl = "$HOSTED_MAP_URL?t=${System.currentTimeMillis()}"
                Log.d(TAG, "Loading map from: $timestampUrl")
                Log.d(TAG, "USER AGENT = ${settings.userAgentString}")

                loadUrl(timestampUrl)
            }
        },
        update = { webView ->
            webView.requestLayout()
            webView.invalidate()
        }
    )
}
