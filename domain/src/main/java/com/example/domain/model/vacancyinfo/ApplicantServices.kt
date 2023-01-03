package com.example.domain.model.vacancyinfo

import com.google.gson.annotations.SerializedName


data class ApplicantServices (

  @SerializedName("target_employer" ) var targetEmployer : TargetEmployer? = TargetEmployer()

)