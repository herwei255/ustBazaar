package com.okit.ustBazaar.utils

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.okit.ustBazaar.room_models.User

object UserPref {
    private val _user = mutableStateOf<User?>(null)
    val user: State<User?> = _user

    fun updateUser(user: User){
        _user.value = user
    }
}