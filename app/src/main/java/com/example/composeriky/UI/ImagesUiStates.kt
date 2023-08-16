package com.example.composeriky.UI

import com.example.composeriky.data.RikyItemResponse


sealed interface ImagesUiStates {
    //object si no recibe datos, data class si sí los recibe
    object Loading:ImagesUiStates
    data class Error(val throwable: Throwable): ImagesUiStates
    data class Success(val imagesList: List<RikyItemResponse>):ImagesUiStates
}
