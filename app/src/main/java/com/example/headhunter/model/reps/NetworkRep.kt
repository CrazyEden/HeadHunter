package com.example.headhunter.model.reps

import com.example.headhunter.model.data.pagerdata.PagerData
import com.example.headhunter.model.data.vacancyinfo.VacancyInfo

interface NetworkRep {
    suspend fun getVacancies(page:Int): PagerData?
    suspend fun getVacancyInfo(id:String):VacancyInfo
}