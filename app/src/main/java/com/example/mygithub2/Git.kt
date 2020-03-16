package com.example.mygithub2

import com.squareup.moshi.Json

data class Git(
    val user:String,
    @Json(name = "avatar_url")
    val imgSrc: String
)