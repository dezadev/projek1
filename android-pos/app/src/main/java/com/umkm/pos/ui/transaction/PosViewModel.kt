package com.umkm.pos.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umkm.pos.domain.model.CartItem
import com.umkm.pos.domain.repository.ProductRepository
import com.umkm.pos.domain.usecase.CheckoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PosViewModel(
    private val productRepository: ProductRepository,
    private val checkoutUseCase: CheckoutUseCase
) : ViewModel() {
    private val _cart = MutableStateFlow<List<CartItem>>(emptyList())
    val cart: StateFlow<List<CartItem>> = _cart.asStateFlow()

    fun addByBarcode(barcode: String) {
        viewModelScope.launch {
            val product = productRepository.getByBarcode(barcode) ?: return@launch
            val existing = _cart.value.firstOrNull { it.productId == product.id }
            _cart.value = if (existing == null) {
                _cart.value + CartItem(product.id, product.name, 1, product.sellPrice)
            } else {
                _cart.value.map {
                    if (it.productId == product.id) it.copy(qty = it.qty + 1) else it
                }
            }
        }
    }

    fun checkout(cashierId: Long, paymentMethod: String) {
        viewModelScope.launch {
            checkoutUseCase(cashierId, _cart.value, paymentMethod)
            _cart.value = emptyList()
        }
    }
}
