package com.example.alex.jokesfrontend.domain

import com.example.alex.jokesfrontend.domain.model.Post
import com.example.alex.jokesfrontend.domain.model.Vote
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NewsService {

    @GET("/posts/")
    fun getAllPosts(): Single<MutableList<Post>>

    @GET("/posts/{id}")
    fun getPostById(@Path("id") id: Int): Single<Post>

    @POST("/posts/vote/")
    fun voteForPost(@Body vote: Vote): Single<String>
}