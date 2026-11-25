package com.example.androidallinone.data.repository

import com.example.androidallinone.data.mapper.toDomain
import com.example.androidallinone.data.remote.ApiService
import com.example.androidallinone.data.remote.ApiServiceProvider
import com.example.androidallinone.domain.model.Post
import com.example.androidallinone.domain.repository.PostsRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsRepositoryImpl(private val api: ApiService = ApiServiceProvider.apiService) :
    PostsRepository {
    override suspend fun getPosts(): Result<List<Post>> = withContext(Dispatchers.IO) {
         try {
            val result = api.getPosts().toDomain()
            Result.success(result)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Result.failure(e)
        }
    }
}