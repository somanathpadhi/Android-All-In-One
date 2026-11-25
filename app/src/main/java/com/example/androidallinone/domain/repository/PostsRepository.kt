package com.example.androidallinone.domain.repository

import com.example.androidallinone.domain.model.Post

interface PostsRepository {
    suspend fun getPosts(): Result<List<Post>>
}