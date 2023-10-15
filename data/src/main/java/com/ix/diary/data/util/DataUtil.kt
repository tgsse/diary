package com.ix.diary.data.util

import com.ix.diary.data.BuildConfig


sealed class DataUtil {
    companion object {
        const val databaseName = "databaseName"
        const val tableName = "table_name"

        const val baseUrl = BuildConfig.baseUrl
    }
}
