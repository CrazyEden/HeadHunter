package com.example.domain.reps

import com.example.domain.model.RoomData

interface LocalDataRep {
    suspend fun getAllVacancies():MutableList<RoomData>?
    suspend fun getListVacanciesId():MutableList<String>?
    suspend fun getVacancyInfo(id:String):RoomData?
    suspend fun insertVacancy(vacancy:RoomData)
    suspend fun removeVacancy(vacancy:RoomData)
}