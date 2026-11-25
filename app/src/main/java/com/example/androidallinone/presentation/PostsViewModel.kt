package com.example.androidallinone.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidallinone.domain.model.Post
import com.example.androidallinone.domain.usecase.PostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostsViewModel(
    private val postsUseCase: PostsUseCase = PostsUseCase()
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Post>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            postsUseCase().onSuccess { data ->
                _uiState.update { UiState.Success(data = data) }
            }.onFailure { error ->
                _uiState.update { UiState.Failure(message = error.message ?: "N/A") }
            }
        }
    }
}