package com.example.domain.usecase

import com.example.domain.reps.NetworkRep
import javax.inject.Inject

class GetVacancyInfoUseCase @Inject constructor(
    private val networkRep: NetworkRep
    ) {
    suspend fun execute(vacancyIdd:String) =
        networkRep.getVacancyInfo(vacancyIdd)
}