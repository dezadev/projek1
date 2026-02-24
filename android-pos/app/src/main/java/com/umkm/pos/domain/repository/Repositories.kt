package com.umkm.pos.domain.repository

import com.umkm.pos.data.local.entity.ProductEntity
import com.umkm.pos.domain.model.CartItem
import com.umkm.pos.domain.model.DashboardSummary
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun observeProducts(): Flow<List<ProductEntity>>
    suspend fun saveProduct(product: ProductEntity): Long
    suspend fun getByBarcode(barcode: String): ProductEntity?
    suspend fun deleteProduct(id: Long)
}

interface SalesRepository {
    suspend fun saveOfflineSale(cartItems: List<CartItem>, cashierId: Long, paymentMethod: String): Long
    suspend fun dashboardSummary(): DashboardSummary
}
