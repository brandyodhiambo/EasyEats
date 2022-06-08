package com.odhiambodevelopers.easyeats.di

import android.content.Context
import com.odhiambodevelopers.easyeats.data.repository.AuthRepository
import com.odhiambodevelopers.easyeats.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository() = AuthRepository()

    @Provides
    @Singleton
    fun provideMainRepository(@ApplicationContext context: Context):MainRepository{
        return MainRepository(context)
    }
}