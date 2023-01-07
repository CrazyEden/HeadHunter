package com.example.domain.usecase.localdata

import com.example.domain.reps.LocalDataRep
import javax.inject.Inject

class GetVacancyUseCase @Inject constructor(
    private val localDataRep: LocalDataRep
) {
    suspend fun execute(id:String) = localDataRep.getVacancyInfo(id)
}