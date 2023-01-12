package com.example.wisdomleaftest.models

//Class utilized for converting JSON data to Kotlin objects using Retrofit.
data class ImageItemResponse(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)