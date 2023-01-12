package com.example.wisdomleaftest.api

import com.example.wisdomleaftest.models.ImageItem
import retrofit2.http.GET

interface ImageApi {

    @GET("/v2/list?page=2&limit=20")
    suspend fun getImages():List<ImageItem>

}