package com.umkm.pos.di

import android.content.Context
import androidx.room.Room
import com.umkm.pos.data.local.AppDatabase
import com.umkm.pos.data.repository.ProductRepositoryImpl
import com.umkm.pos.data.repository.SalesRepositoryImpl
import com.umkm.pos.domain.repository.ProductRepository
import com.umkm.pos.domain.repository.SalesRepository

object AppModule {
    fun provideDb(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "umkm-pos.db"
    ).fallbackToDestructiveMigration().build()

    fun provideProductRepository(db: AppDatabase): ProductRepository = ProductRepositoryImpl(db.productDao())
    fun provideSalesRepository(db: AppDatabase): SalesRepository = SalesRepositoryImpl(db.saleDao())
}
