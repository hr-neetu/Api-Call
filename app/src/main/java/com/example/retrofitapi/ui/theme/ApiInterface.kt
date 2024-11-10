package com.example.retrofitapi.ui.theme

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/posts")
    fun getPosts(): Call<List<PostResponseItem>>

    @GET("/posts/{id}")
    fun getPostById(
        @Path("id") postId: Int
    ): Call<PostResponseItem>
}