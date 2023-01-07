package com.example.data.reps

import com.example.domain.model.RoomDataEntity
import com.example.domain.reps.LocalDataRep
import javax.inject.Inject

class LocalDataRepImpl @Inject constructor(
    private val vacanciesDao: VacanciesDao
) : LocalDataRep {
    override suspend fun getAllVacancies(): MutableList<RoomDataEntity>? {
        return vacanciesDao.getAllVacancies()
    }

    override suspend fun getListVacanciesId(): MutableList<String>? {
        return vacanciesDao.getListId()
    }

    override suspend fun getVacancyInfo(id: String): RoomDataEntity? {
        return vacanciesDao.getVacancyInfo(id)
    }

    override suspend fun insertVacancy(vacancy: RoomDataEntity) {
        vacanciesDao.insertVacancy(vacancy)
    }

    override suspend fun removeVacancy(vacancy: RoomDataEntity) {
        vacanciesDao.removeVacancy(vacancy)
    }
}