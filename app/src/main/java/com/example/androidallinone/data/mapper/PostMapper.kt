package com.example.androidallinone.data.mapper

import com.example.androidallinone.data.model.PostDto
import com.example.androidallinone.domain.model.Post

fun PostDto.toDomain(): Post {
    return Post(
        id = this.id ?: 0,
        title = this.title ?: ""
    )
}

fun List<PostDto>.toDomain(): List<Post> = this.map { it.toDomain() }
