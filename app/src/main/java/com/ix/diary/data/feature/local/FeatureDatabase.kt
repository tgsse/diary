package com.ix.diary.data.feature.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FeatureEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class FeatureDatabase : RoomDatabase() {
    abstract fun dao(): FeatureDao
}
