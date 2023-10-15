package com.ix.diary.data.feature.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ix.diary.data.util.DataUtil.Companion.tableName

@Entity(tableName = tableName)
class FeatureEntity(
    val title: String = "",
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
