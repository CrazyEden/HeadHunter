package com.example.domain.usecase

import com.example.domain.reps.NetworkRep

class GetVacancyInfoUseCase(
    private val networkRep: NetworkRep
    ) {
    suspend fun execute(vacancyIdd:String) =
        networkRep.getVacancyInfo(vacancyIdd)
}