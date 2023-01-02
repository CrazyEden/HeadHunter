package com.example.headhunter.presentation.utils

import android.content.Context
import com.example.headhunter.R


class SearchSettings(val context:Context) {
    val schedule = mapOf<String,String?>(
        context.getString(R.string.full_day_schedule) to "fullDay" ,
        context.getString(R.string.shift_schedule) to "shift" ,
        context.getString(R.string.flexible_schedule) to "flexible" ,
        context.getString(R.string.remote_schedule) to "remote" ,
        context.getString(R.string.fly_in_fly_out_schedule) to "flyInFlyOut"
    )

    val employment = mapOf(
        context.getString(R.string.full_employment) to "full",
        context.getString(R.string.part_employment) to "part",
        context.getString(R.string.project_employment) to "project",
        context.getString(R.string.volunteer_employment) to "volunteer",
        context.getString(R.string.probation_employment) to "probation"
    )
    val experience = mapOf(
        context.getString(R.string.no_experience_experience) to "noExperience",
        context.getString(R.string.between1and3_experience) to "between1And3",
        context.getString(R.string.between3and6_experience) to "between3And6",
        context.getString(R.string.more_than6_experience) to "moreThan6"
    )
}