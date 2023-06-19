package com.example.retrofitandcoilpracticeapp.userinterface

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofitandcoilpracticeapp.userinterface.screen.HomeScreen
import com.example.retrofitandcoilpracticeapp.userinterface.screen.MarsPhotosViewModel

@Composable
fun MarsPhotosApp(){
    val marsViewModel: MarsPhotosViewModel =
        viewModel(factory = MarsPhotosViewModel.Factory)
    HomeScreen(
        marsUiState = marsViewModel.marsUiState,
        retryAction = marsViewModel::getMarsPhotos
    )
}