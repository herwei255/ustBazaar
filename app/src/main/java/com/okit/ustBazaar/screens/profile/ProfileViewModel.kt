package com.okit.ustBazaar.screens.profile


import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okit.ustBazaar.repositories.UserRepository
import com.okit.ustBazaar.utils.LOGGED_USER_ID
import com.okit.ustBazaar.utils.dataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A View model with hiltViewModel annotation that is used to access this view model everywhere needed
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val context: Context,
) : ViewModel() {
    fun logout() {
        viewModelScope.launch {
            context.dataStore.edit {
                it[LOGGED_USER_ID] = -1
            }
        }
    }
}