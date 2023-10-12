package com.ix.diary.data.feature.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface FeatureService {

    @GET("/api/v1/feature/{id}")
    suspend fun get(@Path("id") id: String): Response
}
