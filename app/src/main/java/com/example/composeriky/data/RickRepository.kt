package com.example.composeriky.data

import com.example.composeriky.UI.RikyItem

class RickRepository {

        private val api = RetrofitService()

        suspend fun doListRikyItems(): List<RikyItemResponse>{
            return api.doListRikyItems()
        }

}