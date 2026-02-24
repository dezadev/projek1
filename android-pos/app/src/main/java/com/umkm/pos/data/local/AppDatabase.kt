package com.umkm.pos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.umkm.pos.data.local.dao.ProductDao
import com.umkm.pos.data.local.dao.SaleDao
import com.umkm.pos.data.local.entity.CategoryEntity
import com.umkm.pos.data.local.entity.CustomerEntity
import com.umkm.pos.data.local.entity.ProductEntity
import com.umkm.pos.data.local.entity.SaleEntity
import com.umkm.pos.data.local.entity.SaleItemEntity
import com.umkm.pos.data.local.entity.StockMovementEntity
import com.umkm.pos.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        CategoryEntity::class,
        ProductEntity::class,
        CustomerEntity::class,
        SaleEntity::class,
        SaleItemEntity::class,
        StockMovementEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun saleDao(): SaleDao
}
