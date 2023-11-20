package com.mrntlu.pagination.di

import com.mrntlu.pagination.repository.AssociationManuelPagingRepository
import com.mrntlu.pagination.service.AssociationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun provideNewsManuelPagingRepository(newsApiService: AssociationApiService): AssociationManuelPagingRepository =
        AssociationManuelPagingRepository(newsApiService)
}