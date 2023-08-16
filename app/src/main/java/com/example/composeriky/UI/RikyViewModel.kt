package com.example.composeriky.UI

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeriky.UI.ImagesUiStates.Success
import com.example.composeriky.data.RikyItemResponse
import com.example.composeriky.domain.DoListRikyListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject
import androidx.compose.foundation.lazy.layout.MutableIntervalList
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.composeriky.domain.GetFlowRickyListUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


@HiltViewModel

class RikyViewModel @Inject constructor(
    private val doListRikyListUseCase: DoListRikyListUseCase,
    private val getFlowRickyListUseCase: GetFlowRickyListUseCase
) : ViewModel() {

    // private val _list= MutableLiveData<List<RikyItem>>()
    // var list: LiveData<List<RikyItem>> = _list
//llama a la lista
    fun callList(): List<RikyItemResponse> {
        return runBlocking {
            doListRikyListUseCase()
        }
    }
//llama al flow
    //FlowRoom:
    val usiState: StateFlow<ImagesUiStates> = getFlowRickyListUseCase().map(::Success)
        .catch { ImagesUiStates.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ImagesUiStates.Loading)


}


