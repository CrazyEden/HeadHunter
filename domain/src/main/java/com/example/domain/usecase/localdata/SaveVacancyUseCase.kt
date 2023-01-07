package com.example.domain.usecase.localdata

import com.example.domain.model.RoomData
import com.example.domain.reps.LocalDataRep
import com.example.domain.reps.NetworkRep
import com.example.domain.units.toCompactString
import javax.inject.Inject

class SaveVacancyUseCase @Inject constructor(
    private val localDataRep: LocalDataRep,
    private val networkRep: NetworkRep
) {
    suspend fun execute(roomData:RoomData) =
        localDataRep.insertVacancy(roomData)
    suspend fun execute(id:String){
        val vacancy = networkRep.getVacancyInfo(id)
        localDataRep.insertVacancy(
            RoomData(
                vacancyId = vacancy.id!!,
                vacancyName = vacancy.name!!,
                salary = vacancy.salary.toCompactString,
                experience = vacancy.experience?.name!!,
                schedule = vacancy.schedule?.name!!,
                employer = vacancy.employer?.name!!,
                area = vacancy.area?.name!!,
                descriptions = vacancy.description!!,
                keySkills = vacancy.keySkills.map { it.name!! },
                image = vacancy.employer?.logoUrls?._90

            )
        )
    }
}