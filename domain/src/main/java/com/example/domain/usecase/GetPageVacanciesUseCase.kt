package com.example.domain.usecase

import com.example.domain.model.PagerDataParams
import com.example.domain.reps.NetworkRep

class GetPageVacanciesUseCase(
    private val networkRep: NetworkRep
    ) {
    suspend fun execute(page: Int, params: PagerDataParams) =
        networkRep.getVacancies(page, params)
}