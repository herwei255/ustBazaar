package com.okit.ustBazaar.screens.createproduct

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okit.ustBazaar.repositories.ProductsRepository
import com.okit.ustBazaar.room_models.Product
import com.okit.ustBazaar.sealed.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreateProductViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
): ViewModel() {
    val uiState = mutableStateOf<UiState>(UiState.Idle)

    fun createProdcut(product: Product) {
        viewModelScope.launch {
            val response = productsRepository.createProduct(product)
            println(productsRepository.getProdcuts())
        }
    }
}