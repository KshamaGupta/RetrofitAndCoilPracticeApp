package com.example.retrofitandcoilpracticeapp.data

import com.example.retrofitandcoilpracticeapp.model.MarsPhotos
import com.example.retrofitandcoilpracticeapp.network.MarsApiService

interface MarsPhotosRepositary {
    suspend fun getMarsPhotos():List<MarsPhotos>
}
class NetworkMarsPhotoRepository(private val marsApiService: MarsApiService):MarsPhotosRepositary{
    override suspend fun getMarsPhotos(): List<MarsPhotos> {
        return marsApiService.getPhotos()
    }
}