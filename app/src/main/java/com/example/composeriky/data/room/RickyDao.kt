package com.example.composeriky.data.room

import androidx.room.Dao
import androidx.room.*
import com.example.composeriky.data.RikyItemResponse
import kotlinx.coroutines.flow.Flow


@Dao
public interface RickyDao {
    //Genera la lista de tareas
    @Query("SELECT * from RikyItemResponse")
    fun getTasks(): Flow<List<RikyItemResponse>>
}

