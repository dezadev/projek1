package com.umkm.pos.domain.model

data class DashboardSummary(
    val totalSalesToday: Long,
    val transactionCountToday: Int,
    val bestSellerName: String = "-"
)

data class CartItem(
    val productId: Long,
    val productName: String,
    val qty: Int,
    val price: Long,
    val discount: Long = 0
)
