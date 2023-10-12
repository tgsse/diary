package com.ix.diary.data.feature

import com.ix.diary.data.feature.local.FeatureEntity
import com.ix.diary.data.feature.remote.Feature

fun Feature.toEntity(): FeatureEntity {
    return FeatureEntity(
        title = title,
    )
}
