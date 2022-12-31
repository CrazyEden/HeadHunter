package com.example.headhunter.domain

import com.example.headhunter.model.data.PagerData
import com.example.headhunter.model.reps.NetworkRep
import javax.inject.Inject

class NetworkRepImpl @Inject constructor(
    private val api:Api
):NetworkRep {
    override suspend fun getVacancies(page: Int): PagerData? {
        return api.getPage(page).body()
//        if (result.isSuccessful)
//            return result

    }
}