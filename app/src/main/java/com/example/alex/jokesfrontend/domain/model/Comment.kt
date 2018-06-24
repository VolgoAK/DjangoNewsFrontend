package com.example.alex.jokesfrontend.domain.model

import com.google.gson.annotations.SerializedName

data class Comment(val id: Int = 0,
                   val text: String,
                   @SerializedName("author_name") val authorName: String,
                   @SerializedName("pub_date") val pubDate: String?,
                   @SerializedName("post") val postId: Int)