package com.okit.ustBazaar.screens.signup


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.okit.ustBazaar.repositories.UserRepository
import com.okit.ustBazaar.sealed.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A View model with hiltViewModel annotation that is used to access this view model everywhere needed
 */
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    val uiState = mutableStateOf<UiState>(UiState.Idle)

}