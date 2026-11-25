package com.example.androidallinone.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PostScreen(
    modifier: Modifier = Modifier,
    viewModel: PostsViewModel = viewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        when (val state = state.value) {
            is UiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is UiState.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(
                        items = state.data,
                        key = { post -> post.id }) {
                        Card(
                            modifier = Modifier.padding(8.dp),
                            shape = CardDefaults.elevatedShape
                        ) {
                            Text(text = "${it.id}")
                            Text(text = it.title)
                        }
                    }
                }
            }

            is UiState.Failure -> {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = state.message
                )
            }
        }
    }
}