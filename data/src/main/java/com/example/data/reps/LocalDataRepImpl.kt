package com.example.data.reps

import com.example.domain.model.RoomData
import com.example.domain.reps.LocalDataRep
import javax.inject.Inject

class LocalDataRepImpl @Inject constructor(
    private val vacanciesDao: VacanciesDao
) : LocalDataRep {
    private fun RoomData.toEntity() = RoomDataEntity(
        vacancyId = this.vacancyId,
        vacancyName = this.vacancyName,
        salary = this.salary,
        experience = this.experience,
        schedule = this.schedule,
        employer = this.employer,
        area = this.area,
        descriptions = this.descriptions,
        keySkills = this.keySkills,
        image = this.image
    )
    override suspend fun getAllVacancies(): MutableList<RoomData>? {
        return vacanciesDao.getAllVacancies()?.map { it.toRoomData() }?.toMutableList()
    }

    override suspend fun getListVacanciesId(): MutableList<String>? {
        return vacanciesDao.getListId()
    }

    override suspend fun getVacancyInfo(id: String): RoomData? {
        return vacanciesDao.getVacancyInfo(id)?.toRoomData()
    }

    override suspend fun insertVacancy(vacancy: RoomData) {
        vacanciesDao.insertVacancy(vacancy.toEntity())
    }

    override suspend fun removeVacancy(vacancy: RoomData) {
        vacanciesDao.removeVacancy(vacancy.toEntity())
    }
}