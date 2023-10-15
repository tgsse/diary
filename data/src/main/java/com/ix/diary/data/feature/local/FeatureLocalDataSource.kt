package com.ix.diary.data.feature.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeatureLocalDataSource @Inject constructor(
    private val featureDao: FeatureDao,
) {
    suspend fun load(): FeatureEntity? = withContext(Dispatchers.IO) {
        featureDao.load()
    }

    suspend fun save(e: FeatureEntity) = withContext(Dispatchers.IO) {
        featureDao.save(e)
    }
}
