package com.ix.diary.data.feature.local

import javax.inject.Inject

interface FeatureLocalRepositoryI {
    suspend fun load(): FeatureEntity?
    suspend fun save(featureEntity: FeatureEntity)
}

class FeatureFeatureLocalRepository @Inject constructor(
    private val featureLocalDataSource: FeatureLocalDataSource,
) : FeatureLocalRepositoryI {

    override suspend fun load(): FeatureEntity? {
        return featureLocalDataSource.load()
    }

    override suspend fun save(featureEntity: FeatureEntity) {
        featureLocalDataSource.save(featureEntity)
    }
}
