package com.mrntlu.pagination.di

import com.mrntlu.pagination.service.AssociationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(): AssociationApiService = Retrofit.Builder()
        .baseUrl("https://projects.propublica.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AssociationApiService::class.java)
}