package com.example.data.reps

import com.example.domain.model.PagerDataParamsParcel
import com.example.domain.model.pagerdata.PagerData
import com.example.domain.model.vacancyinfo.VacancyInfo
import com.example.domain.reps.NetworkRep
import javax.inject.Inject

class NetworkRepImpl @Inject constructor(
    private val api: Api
): NetworkRep {
    override suspend fun getVacancies(
        page: Int,
        params: PagerDataParamsParcel
    ): PagerData? {
        return api.getPage(
            page = page,
            text = params.text,
            experienceId = params.experienceId,
            /*employments*/
            employmentId1 = params.employmentIds[0],
            employmentId2 = params.employmentIds[1],
            employmentId3 = params.employmentIds[2],
            employmentId4 = params.employmentIds[3],
            employmentId5 = params.employmentIds[4],
            /*schedules*/
            scheduleId1 = params.scheduleIds[0],
            scheduleId2 = params.scheduleIds[1],
            scheduleId3 = params.scheduleIds[2],
            scheduleId4 = params.scheduleIds[3],
            scheduleId5 = params.scheduleIds[4],

            salary = params.salary,
            onlyWithSalary = params.onlyWithSalary
        ).body()
    }

    override suspend fun getVacancyInfo(id: String): VacancyInfo {
        return api.getVacancy(id).body()!!
    }
}