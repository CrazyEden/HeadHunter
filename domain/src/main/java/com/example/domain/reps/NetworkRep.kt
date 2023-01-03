package com.example.domain.reps

import com.example.domain.model.PagerDataParams
import com.example.domain.model.pagerdata.PagerData
import com.example.domain.model.vacancyinfo.VacancyInfo

interface NetworkRep {
    suspend fun getVacancies(
        page: Int,
        params: PagerDataParams
    ): PagerData?
    suspend fun getVacancyInfo(id:String): VacancyInfo
}