package com.example.composeriky.UI

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.composeriky.data.RikyItemResponse
import com.example.composeriky.domain.DoListRikyListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class RikyViewModel {
    val doListRikyListUseCase= DoListRikyListUseCase()

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