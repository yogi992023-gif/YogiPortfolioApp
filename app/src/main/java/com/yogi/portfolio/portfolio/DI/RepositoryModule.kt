package com.yogi.portfolio.portfolio.DI

import com.yogi.portfolio.portfolio.Repository.CartRepository
import com.yogi.portfolio.portfolio.Repository.CartRepositoryImpl
import com.yogi.portfolio.portfolio.Repository.MenuRepository
import com.yogi.portfolio.portfolio.Repository.MenuRepositoryImpl
import com.yogi.portfolio.portfolio.Repository.ProductRepository
import com.yogi.portfolio.portfolio.Repository.ProductRepositoryImpl
import com.yogi.portfolio.portfolio.data.API.DAO.CartDao
import com.yogi.portfolio.portfolio.data.API.DAO.MenuDao
import com.yogi.portfolio.portfolio.data.API.DAO.ProductDao
import com.yogi.portfolio.portfolio.data.API.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(api: ProductApi, dao: ProductDao): ProductRepository {
        return ProductRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideCartRepository(cartDao: CartDao): CartRepository {
        return CartRepositoryImpl(cartDao)
    }

    @Provides
    @Singleton
    fun provideMenuRepository(menuDao : MenuDao): MenuRepository {
        return MenuRepositoryImpl(menuDao)
    }


}