package com.example.androidallinone.domain.usecase

import com.example.androidallinone.data.repository.PostsRepositoryImpl
import com.example.androidallinone.domain.model.Post
import com.example.androidallinone.domain.repository.PostsRepository

class PostsUseCase(
    private val repository: PostsRepository = PostsRepositoryImpl()
) {
    suspend operator fun invoke(): Result<List<Post>> = repository.getPosts()
}