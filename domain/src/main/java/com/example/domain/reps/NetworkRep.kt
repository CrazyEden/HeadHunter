package com.example.domain.reps

import com.example.domain.model.PagerDataParamsParcel
import com.example.domain.model.pagerdata.PagerData
import com.example.domain.model.vacancyinfo.VacancyInfo

interface NetworkRep {
    suspend fun getVacancies(
        page: Int,
        params: PagerDataParamsParcel
    ): PagerData?
    suspend fun getVacancyInfo(id:String): VacancyInfo
}