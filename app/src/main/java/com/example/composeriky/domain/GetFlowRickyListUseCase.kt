package com.example.composeriky.domain

import com.example.composeriky.data.RickRepository
import com.example.composeriky.data.RikyItemResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



class GetFlowRickyListUseCase @Inject constructor(private val repository: RickRepository) {
    operator fun invoke(): Flow<List<RikyItemResponse>> {
        return repository.getListRickAndMorty
    }
}