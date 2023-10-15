package com.ix.diary.data.feature.remote

import com.ix.diary.data.feature.local.FeatureEntity
import com.ix.diary.dummyFeatureEntity
import kotlinx.coroutines.delay

object FakeFeatureRemoteRepository : com.ix.diary.data.feature.remote.FeatureRemoteRepositoryI {

    override suspend fun fetch(): com.ix.diary.data.feature.local.FeatureEntity {
        delay(10L)
        return dummyFeatureEntity
    }
}
