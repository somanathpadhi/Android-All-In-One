package com.example.androidallinone.presentation

import com.example.androidallinone.domain.model.Post
import com.example.androidallinone.domain.usecase.PostsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PostsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val postsUseCase: PostsUseCase = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchPosts success should update uiState to Success`() = runTest {
        // Given
        val fakePosts = listOf(
            Post(id = 1, title = "First Post"),
            Post(id = 2, title = "Second Post"),
        )
        coEvery { postsUseCase() } returns Result.success(fakePosts)

        // When
        val viewModel = PostsViewModel(postsUseCase)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertTrue(state is UiState.Success)
        assertEquals(fakePosts, (state as UiState.Success).data)
        coVerify(exactly = 1) { postsUseCase() }
    }

    @Test
    fun `fetchPosts failure should update uiState to Failure`() = runTest {
        // Given
        val errorMessage = "Network Connection Failed"
        coEvery { postsUseCase() } returns Result.failure(Exception(errorMessage))

        // When
        val viewModel = PostsViewModel(postsUseCase)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertTrue(state is UiState.Failure)
        assertEquals(errorMessage, (state as UiState.Failure).message)
        coVerify(exactly = 1) { postsUseCase() }
    }
}