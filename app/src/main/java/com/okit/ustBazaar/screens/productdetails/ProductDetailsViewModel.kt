package com.okit.ustBazaar.screens.productdetails


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okit.ustBazaar.room_models.Product
import com.okit.ustBazaar.repositories.ProductsRepository
import com.okit.ustBazaar.sealed.DataResponse
import com.okit.ustBazaar.sealed.Error
import com.okit.ustBazaar.sealed.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A View model with hiltViewModel annotation that is used to access this view model everywhere needed
 */
@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
) : ViewModel() {
    private val _detailsUiState = mutableStateOf<UiState>(UiState.Loading)
    val detailsUiState: State<UiState> = _detailsUiState

    private val _product = mutableStateOf<Product?>(null)
    val product: State<Product?> = _product


    private val _sizeScale = mutableStateOf(1f)
    val sizeScale: State<Float> = _sizeScale

    fun getProductDetails(productId: Int) {
        _detailsUiState.value = UiState.Loading
        viewModelScope.launch {
            //break here
            productsRepository.getProductDetails(productId = productId).let {
                when (it) {
                    is DataResponse.Success -> {
                        /** Got a response from the server successfully */
                        _detailsUiState.value = UiState.Success
                        it.data?.let { product ->
                            _product.value = product
                        }
                    }
                    is DataResponse.Error -> {
                        /** An error happened when fetching data from the server */
                        _detailsUiState.value = UiState.Error(error = it.error ?: Error.Network)
                    }
                }
            }
        }
    }
}