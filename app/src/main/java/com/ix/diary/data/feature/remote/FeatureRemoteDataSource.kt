package com.ix.diary.data.feature.remote

import javax.inject.Inject

class FeatureRemoteDataSource @Inject constructor(
    private val featureService: FeatureService,
) {
    suspend fun fetch(): Feature? {
        val resp = featureService.get("24645")
        return resp.data
    }
}
