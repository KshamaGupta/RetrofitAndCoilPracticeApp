package com.example.retrofitandcoilpracticeapp.userinterface.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.retrofitandcoilpracticeapp.MarsPhotosApplication
import com.example.retrofitandcoilpracticeapp.data.MarsPhotosRepositary
import com.example.retrofitandcoilpracticeapp.model.MarsPhotos
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val photos: List<MarsPhotos>) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class MarsPhotosViewModel(val marsPhotosRepositary: MarsPhotosRepositary):ViewModel() {
    var marsUiState:MarsUiState by mutableStateOf(MarsUiState.Loading)
    init {
        getMarsPhotos()
    }
    fun getMarsPhotos(){
        viewModelScope.launch {
            marsUiState=MarsUiState.Loading
            marsUiState=try{
                MarsUiState.Success(marsPhotosRepositary.getMarsPhotos())
            }catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepositary
                MarsPhotosViewModel(marsPhotosRepository)
            }
        }
    }



}