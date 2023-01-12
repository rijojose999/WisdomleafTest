package com.example.wisdomleaftest.api

import com.example.wisdomleaftest.models.ImageItemResponse
import retrofit2.Response
import retrofit2.http.GET

//Retrofit handles the implementation of these interfaces automatically.
interface ImageApi {

    @GET("/v2/list?page=2&limit=20")
    suspend fun getImages(): Response<List<ImageItemResponse>>

}