package com.ix.diary.data.feature.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FeatureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(e: FeatureEntity)

    @Query("SELECT * FROM table_name")
    suspend fun load(): FeatureEntity?
}
