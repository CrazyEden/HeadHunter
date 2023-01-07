package com.example.domain.usecase.localdata

import com.example.domain.model.RoomDataEntity
import com.example.domain.reps.LocalDataRep
import javax.inject.Inject

class SaveVacancyUseCase @Inject constructor(
    private val localDataRep: LocalDataRep
) {
    suspend fun execute(roomData:RoomDataEntity) =
        localDataRep.insertVacancy(roomData)
}