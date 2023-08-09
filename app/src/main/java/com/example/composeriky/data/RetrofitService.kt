package com.example.composeriky.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun doListRikyItems(): List<RikyItemResponse>{
        //(Dispatchers.IO) en hilo secundario
        return withContext(Dispatchers.IO){
            val response = retrofit.create(RetrofitCLient::class.java).doListRikyItems()
            response.body()?.results ?: emptyList()
        //response.body()?.results ?: emptyList()
        }
    }




}
