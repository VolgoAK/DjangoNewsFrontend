package com.example.alex.jokesfrontend.domain.model

import com.google.gson.annotations.SerializedName


data class Post(val id : Int,
                val body: String,
                @SerializedName("pub_date") val date: String,
                val votes: Int,
                val image: String)