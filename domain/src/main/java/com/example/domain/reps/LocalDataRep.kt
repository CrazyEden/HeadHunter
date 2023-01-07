package com.example.domain.reps

import com.example.domain.model.RoomDataEntity


interface LocalDataRep {
    suspend fun getAllVacancies():MutableList<RoomDataEntity>?
    suspend fun getListVacanciesId():MutableList<String>?
    suspend fun getVacancyInfo(id:String):RoomDataEntity?
    suspend fun insertVacancy(vacancy:RoomDataEntity)
    suspend fun removeVacancy(vacancy:RoomDataEntity)
}