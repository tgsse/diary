package com.ix.diary.data.di

import android.content.Context
import androidx.room.Room
import com.ix.diary.data.feature.local.FeatureDatabase
import com.ix.diary.data.feature.local.FeatureFeatureLocalRepository
import com.ix.diary.data.feature.local.FeatureLocalDataSource
import com.ix.diary.data.feature.local.FeatureLocalRepositoryI
import com.ix.diary.data.util.DataUtil.Companion.databaseName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeatureDatabaseModule {

    @Singleton
    @Provides
    fun provideFeatureDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = FeatureDatabase::class.java,
        name = databaseName,
    ).build()

    @Singleton
    @Provides
    fun provideDao(featureDatabase: FeatureDatabase) = featureDatabase.dao()

    @Singleton
    @Provides
    fun provideFeatureLocalRepository(featureLocalDataSource: FeatureLocalDataSource): FeatureLocalRepositoryI {
        return FeatureFeatureLocalRepository(featureLocalDataSource)
    }
}
