package com.example.composeriky.domain



import androidx.lifecycle.viewModelScope
import com.example.composeriky.UI.ImagesUiStates
import com.example.composeriky.data.RickRepository
import com.example.composeriky.data.RikyItemResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetFlowRickyListUseCaseTest{

    //lateinit del repository(dnd coges la acción, los datos)
    //lateinitn del uso de caso, lo q styás testeando
    @RelaxedMockK
    private lateinit var rickRepository: RickRepository
    lateinit var getFlowRickyListUseCase: GetFlowRickyListUseCase

    //En before, se pone lo q se hace antes de empezar el test
    // inicia del mokitoy el uso de caso
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getFlowRickyListUseCase = GetFlowRickyListUseCase(rickRepository)
    }
    //runBlocking pq hay corrutinas
    @Test
    fun whenAppIsSuccess_thenGetRickFlow() = runBlocking{
        val list: List<RikyItemResponse> = listOf(RikyItemResponse(1, "name1", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),RikyItemResponse(2, "name2", "https://rickandmortyapi.com/api/character/avatar/3.jpeg"))


        //Given
        //Corrutina: coEvery, sino Every
        // si no devuelv nada no poner esta parte de Given. aunq haya un return me refiero si en la fun del caso de uso no tiene los dos puntos q //indique q'es lo q devuelve
            val flowList = flow<List<RikyItemResponse>> { when(1+1){2->list
            else -> emptyList()
            }
            }
            every { rickRepository.getListRickAndMorty} returns flowList

        //When
        getFlowRickyListUseCase()

        //Then
        ////Corrutina: coVerify, sino Verify
        verify(exactly = 1) { rickRepository.getListRickAndMorty }
    }

}