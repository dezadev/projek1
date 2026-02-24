package com.umkm.pos.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umkm.pos.data.local.entity.ProductEntity
import com.umkm.pos.domain.repository.ProductRepository
import com.umkm.pos.domain.usecase.SaveProductUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepository: ProductRepository,
    private val saveProductUseCase: SaveProductUseCase
) : ViewModel() {
    val products: StateFlow<List<ProductEntity>> = productRepository.observeProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun save(product: ProductEntity) {
        viewModelScope.launch { saveProductUseCase(product) }
    }

    fun delete(id: Long) {
        viewModelScope.launch { productRepository.deleteProduct(id) }
    }
}
