package com.example.retrofitandcoilpracticeapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MarsPhotos(
    val id:Int,

@SerialName(value = "img_src")
    val imgsrc:String
)
