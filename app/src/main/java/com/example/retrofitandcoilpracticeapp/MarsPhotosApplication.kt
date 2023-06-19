package com.example.retrofitandcoilpracticeapp

import android.app.Application
import com.example.retrofitandcoilpracticeapp.data.AppContainer
import com.example.retrofitandcoilpracticeapp.data.DefaultAppContainer

class MarsPhotosApplication:Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}