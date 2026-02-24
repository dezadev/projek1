package com.umkm.pos.domain.usecase

import com.umkm.pos.data.local.entity.ProductEntity
import com.umkm.pos.domain.model.CartItem
import com.umkm.pos.domain.repository.ProductRepository
import com.umkm.pos.domain.repository.SalesRepository

class SaveProductUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(product: ProductEntity): Long {
        require(product.name.isNotBlank()) { "Nama produk wajib diisi" }
        require(product.sellPrice > 0) { "Harga jual harus lebih dari 0" }
        return productRepository.saveProduct(product)
    }
}

class CheckoutUseCase(private val salesRepository: SalesRepository) {
    suspend operator fun invoke(cashierId: Long, items: List<CartItem>, paymentMethod: String): Long {
        require(items.isNotEmpty()) { "Keranjang tidak boleh kosong" }
        return salesRepository.saveOfflineSale(items, cashierId, paymentMethod)
    }
}
