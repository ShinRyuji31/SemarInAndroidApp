package com.example.application.global.ui.component

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
import com.example.application.anterin.data.model.MapLocation
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import org.json.JSONObject

private const val TAG = "MapWebView"

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

    // Handle Markers
    LaunchedEffect(pickupLocation, destinationLocation, isMapReady) {
        if (isMapReady) {
            val pJson = pickupLocation?.let {
                JSONObject(Json.encodeToString(it)).toString()
            } ?: ""

            val dJson = destinationLocation?.let {
                JSONObject(Json.encodeToString(it)).toString()
            } ?: ""

            webViewRef?.evaluateJavascript(
                "setMarkers('${pJson}', '${dJson}')",
                null
            )
        }
    }

    // Handle Routing
    LaunchedEffect(routeGeoJson, isMapReady) {
        if (isMapReady) {
            if (routeGeoJson != null) {
                val escapedGeoJson =
                    routeGeoJson
                        ?.replace("\\", "\\\\")
                        ?.replace("'", "\\'")

                webViewRef?.evaluateJavascript(
                    "drawRoute('$escapedGeoJson')",
                    null
                )
            } else {
                webViewRef?.evaluateJavascript("clearRoute()", null)
            }
        }
    }

    // Handle Interaction
    LaunchedEffect(isInteractionEnabled, isMapReady) {
        if (isMapReady) {
            webViewRef?.evaluateJavascript("setInteractionEnabled($isInteractionEnabled)", null)
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
                }
                
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        isMapReady = true
                        Log.d(TAG, "Page finished loading - Map Ready")
                    }
                }
                
                webChromeClient = object : WebChromeClient() {
                    override fun onConsoleMessage(consoleMessage: android.webkit.ConsoleMessage?): Boolean {
                        Log.d(TAG, "JS [${consoleMessage?.messageLevel()}]: ${consoleMessage?.message()}")
                        return true
                    }
                }

                addJavascriptInterface(object {
                    @JavascriptInterface
                    fun onMapClick(lat: Double, lng: Double) {
                        post { onMapClick(lat, lng) }
                    }
                }, "AndroidInterface")

                loadUrl("file:///android_asset/map.html")
            }
        },
        update = { webView ->
            webView.requestLayout()
            webView.invalidate()
        }
    )
}
