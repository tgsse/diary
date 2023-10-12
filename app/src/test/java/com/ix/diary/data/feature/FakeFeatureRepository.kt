package com.ix.diary.data.feature

import com.ix.diary.data.feature.local.FakeFeatureLocalRepository
import com.ix.diary.data.feature.local.FeatureLocalRepositoryI
import com.ix.diary.data.feature.remote.FakeFeatureRemoteRepository
import com.ix.diary.data.feature.remote.FeatureRemoteRepositoryI

class FakeFeatureRepository : FeatureRepositoryI {
    override val remote: FeatureRemoteRepositoryI
        get() = FakeFeatureRemoteRepository
    override val local: FeatureLocalRepositoryI
        get() = FakeFeatureLocalRepository
}
