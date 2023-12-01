package com.okit.ustBazaar.screens.settings;

import androidx.lifecycle.ViewModel
import com.okit.ustBazaar.sealed.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A View model with hiltViewModel annotation that is used to access this view model everywhere needed
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(): ViewModel() {
    val uiState = mutableListOf<UiState>(UiState.Idle)
}
