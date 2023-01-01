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
    suspend fun getPage(@Query("page") page: Int):Response<PagerData>

    @GET("vacancies/{vacancy_id}")
    suspend fun getVacancy(@Path("vacancy_id")vacancyId:String):Response<VacancyInfo>
}
