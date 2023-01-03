package com.example.domain.model.vacancyinfo

import com.google.gson.annotations.SerializedName


data class WorkingTimeIntervals (

  @SerializedName("id"   ) var id   : String? = null,
  @SerializedName("name" ) var name : String? = null

)