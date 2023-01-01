package com.example.headhunter.model.data.vacancyinfo

import com.google.gson.annotations.SerializedName


data class ApplicantServices (

  @SerializedName("target_employer" ) var targetEmployer : TargetEmployer? = TargetEmployer()

)