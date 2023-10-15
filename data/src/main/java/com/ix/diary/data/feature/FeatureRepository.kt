package com.ix.diary.data.feature

import com.ix.diary.data.feature.local.FeatureLocalRepositoryI
import com.ix.diary.data.feature.remote.FeatureRemoteRepositoryI
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface FeatureRepositoryI {
    val remote: FeatureRemoteRepositoryI
    val local: FeatureLocalRepositoryI
}

@ViewModelScoped
class FeatureRepository @Inject constructor(
    remoteRepository: FeatureRemoteRepositoryI,
    localRepository: FeatureLocalRepositoryI,
) : FeatureRepositoryI {
    override val remote = remoteRepository
    override val local = localRepository
}
