package com.umkm.pos.data.repository

import com.umkm.pos.data.local.dao.ProductDao
import com.umkm.pos.data.local.dao.SaleDao
import com.umkm.pos.data.local.entity.ProductEntity
import com.umkm.pos.data.local.entity.SaleEntity
import com.umkm.pos.data.local.entity.SaleItemEntity
import com.umkm.pos.domain.model.CartItem
import com.umkm.pos.domain.model.DashboardSummary
import com.umkm.pos.domain.repository.ProductRepository
import com.umkm.pos.domain.repository.SalesRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class ProductRepositoryImpl(
    private val productDao: ProductDao
) : ProductRepository {
    override fun observeProducts(): Flow<List<ProductEntity>> = productDao.observeProducts()

    override suspend fun saveProduct(product: ProductEntity): Long = productDao.upsert(product)

    override suspend fun getByBarcode(barcode: String): ProductEntity? = productDao.findByBarcode(barcode)

    override suspend fun deleteProduct(id: Long) = productDao.delete(id)
}

class SalesRepositoryImpl(
    private val saleDao: SaleDao
) : SalesRepository {
    override suspend fun saveOfflineSale(cartItems: List<CartItem>, cashierId: Long, paymentMethod: String): Long {
        val subTotal = cartItems.sumOf { (it.qty * it.price) - it.discount }
        val saleId = saleDao.insertSale(
            SaleEntity(
                invoiceNo = "INV-${UUID.randomUUID().toString().take(8)}",
                cashierId = cashierId,
                total = subTotal,
                discount = cartItems.sumOf { it.discount },
                tax = 0,
                paymentMethod = paymentMethod
            )
        )
        saleDao.insertItems(
            cartItems.map {
                SaleItemEntity(
                    saleId = saleId,
                    productId = it.productId,
                    qty = it.qty,
                    itemPrice = it.price,
                    itemDiscount = it.discount
                )
            }
        )
        return saleId
    }

    override suspend fun dashboardSummary(): DashboardSummary = DashboardSummary(
        totalSalesToday = saleDao.totalSalesToday(),
        transactionCountToday = saleDao.transactionCountToday(),
        bestSellerName = "Segera hitung dari query agregasi item"
    )
}
