package com.example.androidallinone.data.remote

import com.example.androidallinone.data.model.PostDto
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    suspend fun getPosts(): List<PostDto>
}