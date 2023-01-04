package com.example.domain.usecase

import com.example.domain.model.PagerDataParams
import com.example.domain.reps.NetworkRep
import javax.inject.Inject

class GetPageVacanciesUseCase @Inject constructor(
    private val networkRep: NetworkRep
    ) {
    suspend fun execute(page: Int, params: PagerDataParams) =
        networkRep.getVacancies(page, params)
}