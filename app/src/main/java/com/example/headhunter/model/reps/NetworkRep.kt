package com.example.headhunter.model.reps

import com.example.headhunter.model.data.PagerData

interface NetworkRep {
    suspend fun getVacancies(page:Int): PagerData?
}