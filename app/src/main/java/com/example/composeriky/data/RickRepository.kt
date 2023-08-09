package com.example.composeriky.data

import com.example.composeriky.UI.RikyItem
import javax.inject.Inject

class RickRepository @Inject constructor(private val api: RetrofitService){


        suspend fun doListRikyItems(): List<RikyItemResponse>{
            return api.doListRikyItems()
        }

}