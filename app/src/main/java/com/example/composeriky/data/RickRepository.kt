package com.example.composeriky.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import androidx.room.*
import com.example.composeriky.data.room.RickyDao
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

class RickRepository @Inject constructor(private val api: RetrofitService, private var rickyDao: RickyDao){



        suspend fun doListRikyItems(): List<RikyItemResponse>{
            return api.doListRikyItems()
        }

//FlowRoom:
    val getListRickAndMorty: Flow<List<RikyItemResponse>> = rickyDao.getTasks()
}