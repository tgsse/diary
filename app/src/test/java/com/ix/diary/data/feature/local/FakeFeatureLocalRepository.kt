package com.ix.diary.data.feature.local

import kotlinx.coroutines.delay

object FakeFeatureLocalRepository : FeatureLocalRepositoryI {

    private var featureEntity: FeatureEntity? = null

    override suspend fun load(): FeatureEntity? {
        delay(10L)
        return featureEntity
    }

    override suspend fun save(entity: FeatureEntity) {
        delay(10L)
        featureEntity = entity
    }
}
