package com.ix.diary.data.feature.remote

import com.ix.diary.data.feature.local.FeatureEntity
import com.ix.diary.dummyFeatureEntity
import kotlinx.coroutines.delay

object FakeFeatureRemoteRepository : FeatureRemoteRepositoryI {

    override suspend fun fetch(): FeatureEntity {
        delay(10L)
        return dummyFeatureEntity
    }
}
