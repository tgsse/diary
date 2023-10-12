package com.ix.diary.data.di

import com.ix.diary.data.feature.FeatureRepository
import com.ix.diary.data.feature.FeatureRepositoryI
import com.ix.diary.data.feature.local.FeatureLocalRepositoryI
import com.ix.diary.data.feature.remote.FeatureFeatureRemoteRepository
import com.ix.diary.data.feature.remote.FeatureRemoteDataSource
import com.ix.diary.data.feature.remote.FeatureRemoteRepositoryI
import com.ix.diary.data.feature.remote.FeatureService
import com.ix.diary.data.util.DataUtil.Companion.baseUrl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeatureNetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideJsonConverter(): Converter.Factory {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        jsonConverter: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(jsonConverter)
            .build()
    }

    @Singleton
    @Provides
    fun provideFeatureService(retrofit: Retrofit): FeatureService {
        return retrofit.create(FeatureService::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteRepository(featureRemoteDataSource: FeatureRemoteDataSource): FeatureRemoteRepositoryI {
        return FeatureFeatureRemoteRepository(featureRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideRepository(featureLocalRepository: FeatureLocalRepositoryI, featureRemoteRepository: FeatureRemoteRepositoryI): FeatureRepositoryI {
        return FeatureRepository(featureRemoteRepository, featureLocalRepository)
    }
}
