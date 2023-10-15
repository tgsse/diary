package com.ix.diary.data.feature.local

import kotlinx.coroutines.delay

object FakeFeatureLocalRepository : com.ix.diary.data.feature.local.FeatureLocalRepositoryI {

    private var featureEntity: com.ix.diary.data.feature.local.FeatureEntity? = null

    override suspend fun load(): com.ix.diary.data.feature.local.FeatureEntity? {
        delay(10L)
        return featureEntity
    }

    override suspend fun save(entity: com.ix.diary.data.feature.local.FeatureEntity) {
        delay(10L)
        featureEntity = entity
    }
}
