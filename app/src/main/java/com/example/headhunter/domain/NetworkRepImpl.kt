package com.example.headhunter.domain

import com.example.headhunter.model.data.pagerdata.PagerData
import com.example.headhunter.model.data.vacancyinfo.VacancyInfo
import com.example.headhunter.model.reps.NetworkRep
import javax.inject.Inject

class NetworkRepImpl @Inject constructor(
    private val api:Api
):NetworkRep {
    override suspend fun getVacancies(page: Int): PagerData? {
        return api.getPage(page).body()
    }

    override suspend fun getVacancyInfo(id: String): VacancyInfo {
        val res = api.getVacancy(id)
        if (res.isSuccessful) return res.body()!!
        else throw TODO()
    }
}