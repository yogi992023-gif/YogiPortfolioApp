package com.yogi.portfolio.portfolio.DI

import android.content.Context
import androidx.room.Room
import com.yogi.portfolio.portfolio.data.API.DB.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "ecommerce_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideProductDao(db: AppDatabase) = db.productDao()

    @Provides
    fun provideCartDao(db: AppDatabase) = db.cartDao()
}