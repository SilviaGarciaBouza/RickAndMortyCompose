package com.example.composeriky.data



import retrofit2.Response
import retrofit2.http.GET

interface RetrofitCLient {
    //Segunda parte de la url empezando con /
    @GET(value= "/api/character")

    //En hilo secundario. Devuelve un response del tipo de datos retrofitResponse
   suspend fun doListRikyItems():Response<RetrofitResponse>



    }
