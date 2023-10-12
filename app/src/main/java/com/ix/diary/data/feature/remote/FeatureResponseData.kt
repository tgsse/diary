package com.ix.diary.data.feature.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerialName("data")
    val data: Feature?,
)

@Serializable
data class Feature(

    @SerialName("title")
    val title: String,
)
