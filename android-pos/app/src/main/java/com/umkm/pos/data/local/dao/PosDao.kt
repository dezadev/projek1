package com.umkm.pos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.umkm.pos.data.local.entity.ProductEntity
import com.umkm.pos.data.local.entity.SaleEntity
import com.umkm.pos.data.local.entity.SaleItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY name ASC")
    fun observeProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE barcode = :barcode LIMIT 1")
    suspend fun findByBarcode(barcode: String): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(product: ProductEntity): Long

    @Update
    suspend fun update(product: ProductEntity)

    @Query("DELETE FROM products WHERE id = :id")
    suspend fun delete(id: Long)
}

@Dao
interface SaleDao {
    @Insert
    suspend fun insertSale(sale: SaleEntity): Long

    @Insert
    suspend fun insertItems(items: List<SaleItemEntity>)

    @Query("SELECT * FROM sales WHERE syncStatus = 'PENDING'")
    suspend fun getPendingSales(): List<SaleEntity>

    @Query("SELECT COUNT(id) FROM sales WHERE date(createdAt / 1000, 'unixepoch', 'localtime') = date('now', 'localtime')")
    suspend fun transactionCountToday(): Int

    @Query("SELECT COALESCE(SUM(total), 0) FROM sales WHERE date(createdAt / 1000, 'unixepoch', 'localtime') = date('now', 'localtime')")
    suspend fun totalSalesToday(): Long
}
