package com.example.data.reps



import androidx.room.*

@Dao
interface VacanciesDao {
    @Query("SELECT * FROM vacancies")
    suspend fun getAllVacancies(): MutableList<RoomDataEntity>?

    @Query("SELECT * FROM vacancies WHERE vacancy_id = :id")
    suspend fun getVacancyInfo(id: String): RoomDataEntity?

    @Query("SELECT vacancy_id From vacancies")
    suspend fun getListId():MutableList<String>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancy: RoomDataEntity)

    @Delete
    suspend fun removeVacancy(vacancy: RoomDataEntity)
}