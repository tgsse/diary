package com.ix.diary.data.feature.local

import androidx.test.filters.SmallTest
import com.ix.diary.dummyFeatureEntity
import com.ix.diary.testDbName
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class FeatureLocalDataSourceTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named(testDbName)
    lateinit var featureDatabase: FeatureDatabase

    private lateinit var featureDao: FeatureDao

    @Before
    fun setup() {
        hiltRule.inject()

        featureDao = featureDatabase.dao()
    }

    @After
    fun tearDown() {
        featureDatabase.close()
    }

    @Test
    fun saveAndLoadFeature() = runTest {
        // GIVEN
        assertNull(featureDao.load())

        // WHEN
        featureDao.save(dummyFeatureEntity)

        // THEN
        val storedValue = featureDao.load()
        assertNotNull(storedValue)
        assertEquals(dummyFeatureEntity.title, storedValue!!.title)
    }
}
