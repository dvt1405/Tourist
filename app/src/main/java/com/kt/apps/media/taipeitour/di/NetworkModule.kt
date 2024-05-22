package com.kt.apps.media.taipeitour.di

import com.google.gson.GsonBuilder
import com.kt.apps.media.taipeitour.Constants
import com.kt.apps.media.taipeitour.data.API
import com.kt.apps.media.taipeitour.di.scope.coroutinescope.CoroutineDispatcherQualifier
import com.kt.apps.media.taipeitour.di.scope.coroutinescope.CoroutineDispatcherType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpLogger(): Interceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Singleton
    @Provides
    fun provideHttpClient(
        provideHttpLogger: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideHttpLogger)
            .build()
    }

    @Provides
    @CoroutineDispatcherQualifier(CoroutineDispatcherType.IO)
    fun providesDispatcherIO(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    @CoroutineDispatcherQualifier(CoroutineDispatcherType.DEFAULT)
    fun providesDispatcherDefault(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @CoroutineDispatcherQualifier(CoroutineDispatcherType.MAIN)
    fun providesDispatcherMain(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    fun provideAPI(
        client: OkHttpClient
    ): API {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(API::class.java)
    }

}