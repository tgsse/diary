package com.ix.diary.data.feature

import com.ix.diary.data.feature.local.FakeFeatureLocalRepository
import com.ix.diary.data.feature.local.FeatureLocalRepositoryI
import com.ix.diary.data.feature.remote.FakeFeatureRemoteRepository
import com.ix.diary.data.feature.remote.FeatureRemoteRepositoryI

class FakeFeatureRepository : com.ix.diary.data.feature.FeatureRepositoryI {
    override val remote: com.ix.diary.data.feature.remote.FeatureRemoteRepositoryI
        get() = FakeFeatureRemoteRepository
    override val local: com.ix.diary.data.feature.local.FeatureLocalRepositoryI
        get() = FakeFeatureLocalRepository
}
