package com.example.composeriky.domain

import com.example.composeriky.data.RickRepository
import com.example.composeriky.data.RikyItemResponse
import javax.inject.Inject


class DoListRikyListUseCase @Inject constructor(private val repository: RickRepository) {

//se llama al repository


    suspend operator fun invoke(): List<RikyItemResponse> {
//aquí la lógica, la acción pero en este caso solo es si la respuesta es false o true
        return repository.doListRikyItems()
    }
}
