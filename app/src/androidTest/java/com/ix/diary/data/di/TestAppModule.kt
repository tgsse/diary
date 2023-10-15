package com.ix.diary.data.di

import android.content.Context
import androidx.room.Room
import com.ix.diary.data.feature.local.FeatureDatabase
import com.ix.diary.testDbName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Singleton
    @Provides
    @Named(testDbName)
    fun injectInMemoryRoom(@ApplicationContext context: Context) = Room
        .inMemoryDatabaseBuilder(
            context = context,
            klass = com.ix.diary.data.feature.local.FeatureDatabase::class.java,
        ).build()
}
