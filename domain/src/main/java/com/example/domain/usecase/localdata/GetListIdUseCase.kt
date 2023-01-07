package com.example.domain.usecase.localdata

import com.example.domain.reps.LocalDataRep
import javax.inject.Inject

class GetListIdUseCase @Inject constructor(
    private val localDataRep: LocalDataRep
) {
    suspend fun execute() = localDataRep.getListVacanciesId()
}