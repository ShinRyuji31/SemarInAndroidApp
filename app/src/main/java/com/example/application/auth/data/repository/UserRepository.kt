package com.example.application.auth.data.repository

import com.example.application.R
import com.example.application.auth.data.model.User

class UserRepository {
    fun getUser(): User {
        return User(
            id = 1,
            name = "Jackowi",
            phoneNumber = "081234567890",
            address = "Gg. Kutai Utara No.1",
            profileImage = R.drawable.change_this_to_correct_pic
        )
    }
}