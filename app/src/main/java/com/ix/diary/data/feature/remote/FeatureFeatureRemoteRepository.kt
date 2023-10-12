package com.ix.diary.data.feature.remote

import com.ix.diary.data.feature.local.FeatureEntity
import com.ix.diary.data.feature.toEntity
import javax.inject.Inject

interface FeatureRemoteRepositoryI {
    suspend fun fetch(): FeatureEntity?
}

class FeatureFeatureRemoteRepository @Inject constructor(
    private val featureRemoteDataSource: FeatureRemoteDataSource,
) : FeatureRemoteRepositoryI {

    override suspend fun fetch(): FeatureEntity? {
        val response = featureRemoteDataSource.fetch()
        return response?.toEntity()
    }
}
