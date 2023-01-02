package com.example.headhunter.domain

import com.example.headhunter.model.data.pagerdata.PagerData
import com.example.headhunter.model.data.vacancyinfo.VacancyInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("vacancies")
    @Headers("User-Agent: HeadHunter/1.0 (crazyeden@yandex.ru)")
    suspend fun getPage(
        @Query("page") page: Int,
        @Query("text") text: String?,
        @Query("experience") experienceId:String?,

        @Query("employment") employmentId1:String?,
        @Query("employment") employmentId2:String?,
        @Query("employment") employmentId3:String?,
        @Query("employment") employmentId4:String?,
        @Query("employment") employmentId5:String?,

        @Query("schedule") scheduleId1:String?,
        @Query("schedule") scheduleId2:String?,
        @Query("schedule") scheduleId3:String?,
        @Query("schedule") scheduleId4:String?,
        @Query("schedule") scheduleId5:String?,

        @Query("salary") salary:Int?,
        @Query("only_with_salary") onlyWithSalary:Boolean?
    ):Response<PagerData>

    @GET("vacancies/{vacancy_id}")
    suspend fun getVacancy(@Path("vacancy_id")vacancyId:String):Response<VacancyInfo>
}
