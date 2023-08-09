package com.example.composeriky.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitService @Inject constructor(private val retrofit: Retrofit) {
    suspend fun doListRikyItems(): List<RikyItemResponse>{
        //(Dispatchers.IO) en hilo secundario
        return withContext(Dispatchers.IO){
            val response = retrofit.create(RetrofitCLient::class.java).doListRikyItems()
            response.body()?.results ?: emptyList()
        //response.body()?.results ?: emptyList()
        }
    }




}
