package com.kt.apps.media.taipeitour.di

import com.kt.apps.media.taipeitour.data.repository.ITourRepository
import com.kt.apps.media.taipeitour.repository.TourRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindTourRepository(
        tourRepositoryImpl: TourRepositoryImpl
    ): ITourRepository
}