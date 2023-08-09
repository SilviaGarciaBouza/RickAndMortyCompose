package com.example.composeriky.UI

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composeriky.data.RikyItemResponse
import com.example.composeriky.domain.DoListRikyListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel

class RikyViewModel @Inject constructor(private val doListRikyListUseCase: DoListRikyListUseCase): ViewModel() {

   // private val _list= MutableLiveData<List<RikyItem>>()
   // var list: LiveData<List<RikyItem>> = _list

    fun callList():List<RikyItemResponse>{
        return runBlocking{
            doListRikyListUseCase()
        }
    }


    //runBlocking { viewModel.doListRikyListUseCase }

    /*//par q sea en hilo secundario:
        viewModelScope.launch {
            _selectedButton.value = false
            val result = doListRikyListUseCase
            if (result){
                //la acción, como navegar a...
                Log.i("test", "Its OK")
            }
            _selectedButton.value= true
        }

*/

    }