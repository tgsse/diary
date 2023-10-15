package com.ix.diary.viewmodel

import com.ix.diary.data.feature.FeatureRepository
import com.ix.diary.data.feature.local.FeatureLocalRepositoryI
import com.ix.diary.data.feature.remote.FeatureRemoteRepositoryI
import com.ix.diary.dummyFeatureEntity
import com.ix.diary.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var remoteRepo: com.ix.diary.data.feature.remote.FeatureRemoteRepositoryI

    @MockK
    private lateinit var localRepo: com.ix.diary.data.feature.local.FeatureLocalRepositoryI

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(
            com.ix.diary.data.feature.FeatureRepository(
                remoteRepo,
                localRepo
            )
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `loading state should be false after init event`() = runTest {
        // GIVEN
        assertTrue(viewModel.state.value.isLoading)
        coEvery {
            localRepo.load()
        } returns null
        coEvery {
            remoteRepo.fetch()
        } returns null

        // WHEN
        viewModel.onEvent(MainEvent.Init)

        // THEN
        advanceUntilIdle()
        assertFalse(viewModel.state.value.isLoading)
    }

    @Test
    fun `local entity exists then init event should load feature from local`() = runTest {
        // GIVEN
        assertNull(viewModel.state.value.featureEntity)
        coEvery {
            localRepo.load()
        } returns dummyFeatureEntity

        // WHEN
        viewModel.onEvent(MainEvent.Init)

        // THEN
        advanceUntilIdle()
        coVerify(exactly = 1) {
            localRepo.load()
        }
        assertEquals(dummyFeatureEntity, viewModel.state.value.featureEntity)
    }

    @Test
    fun `no local entity exists then init event should load feature from remote`() = runTest {
        // GIVEN
        assertNull(viewModel.state.value.featureEntity)
        coEvery {
            localRepo.load()
        } returns null
        coEvery {
            remoteRepo.fetch()
        } returns dummyFeatureEntity
        coEvery {
            localRepo.save(dummyFeatureEntity)
        } returns Unit

        // WHEN
        viewModel.onEvent(MainEvent.Init)

        // THEN
        advanceUntilIdle()
        coVerify(exactly = 1) {
            remoteRepo.fetch()
        }
        coVerify(exactly = 1) {
            localRepo.load()
        }
        coVerify(exactly = 1) {
            localRepo.save(dummyFeatureEntity)
        }
        assertEquals(dummyFeatureEntity, viewModel.state.value.featureEntity)
    }
}
