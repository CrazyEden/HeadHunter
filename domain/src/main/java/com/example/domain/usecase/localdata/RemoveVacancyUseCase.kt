package com.example.domain.usecase.localdata

import com.example.domain.model.RoomData
import com.example.domain.reps.LocalDataRep
import javax.inject.Inject

class RemoveVacancyUseCase @Inject constructor(
    private val localDataRep: LocalDataRep,
    private val getVacancyUseCase: GetVacancyUseCase
) {
    suspend fun execute(vacancy:RoomData) = localDataRep.removeVacancy(vacancy)
    suspend fun execute(id:String){
        val vacancy = getVacancyUseCase.execute(id)
        localDataRep.removeVacancy(vacancy ?: throw NullPointerException("RemoveVacancyUseCase : vacancy wasnt find"))
    }
}