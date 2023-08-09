package com.example.composeriky.domain

import com.example.composeriky.data.RickRepository
import com.example.composeriky.data.RikyItemResponse


class DoListRikyListUseCase {

//se llama al repository

    private val repository = RickRepository()

    suspend operator fun invoke(): List<RikyItemResponse> {
//aquí la lógica, la acción pero en este caso solo es si la respuesta es false o true
        return repository.doListRikyItems()
    }
}
