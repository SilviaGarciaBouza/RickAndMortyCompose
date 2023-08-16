package com.example.composeriky.domain

import com.example.composeriky.data.RickRepository
import com.example.composeriky.data.RikyItemResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class DoListRikyListUseCaseTest{

    //lateinit del repository(dnd coges la acción, los datos)
    //lateinitn del uso de caso, lo q styás testeando
    @RelaxedMockK
    private lateinit var rickRepository: RickRepository
    lateinit var doListRikyListUseCase: DoListRikyListUseCase

    //En before, se pone lo q se hace antes de empezar el test
    // inicia del mokitoy el uso de caso
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        doListRikyListUseCase = DoListRikyListUseCase(rickRepository)
    }
    //runBlocking pq hay corrutinas
    @Test
    fun whenAppIsSuccess_thenGetRickList() = runBlocking{
        val list: List<RikyItemResponse> = listOf(RikyItemResponse(1, "name1", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),RikyItemResponse(2, "name2", "https://rickandmortyapi.com/api/character/avatar/3.jpeg"))
        //Given
        //Corrutina: coEvery, sino Every
        // si no devuelv nada no poner esta parte de Given. aunq haya un return me refiero si en la fun del caso de uso no tiene los dos puntos q //indique q'es lo q devuelve
        coEvery { rickRepository.doListRikyItems()} returns list

        //When
        doListRikyListUseCase()

        //Then
        ////Corrutina: coVerify, sino Verify
        coVerify(exactly = 1) { rickRepository.doListRikyItems() }
    }

}


