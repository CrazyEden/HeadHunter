package com.example.domain.usecase

import com.example.domain.model.PagerDataParamsParcel
import com.example.domain.model.pagerdata.PagerData
import com.example.domain.reps.NetworkRep
import javax.inject.Inject

class GetPageVacanciesUseCase @Inject constructor(
    private val networkRep: NetworkRep
    ) {
    suspend fun execute(page: Int, params: PagerDataParamsParcel): PagerData? =
        networkRep.getVacancies(page, params)
}