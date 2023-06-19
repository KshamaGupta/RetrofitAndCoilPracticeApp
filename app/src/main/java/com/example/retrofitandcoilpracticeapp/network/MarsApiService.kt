package com.example.retrofitandcoilpracticeapp.network

import com.example.retrofitandcoilpracticeapp.model.MarsPhotos
import retrofit2.http.GET

interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos():List<MarsPhotos>
}