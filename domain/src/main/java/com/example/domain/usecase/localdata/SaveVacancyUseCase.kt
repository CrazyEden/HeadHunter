package com.example.domain.usecase.localdata

import com.example.domain.model.RoomData
import com.example.domain.reps.LocalDataRep
import javax.inject.Inject

class SaveVacancyUseCase @Inject constructor(
    private val localDataRep: LocalDataRep
) {
    suspend fun execute(roomData:RoomData) =
        localDataRep.insertVacancy(roomData)
}