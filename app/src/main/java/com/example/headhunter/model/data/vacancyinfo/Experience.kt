package com.example.headhunter.model.data.vacancyinfo

import com.google.gson.annotations.SerializedName


data class Experience (

  @SerializedName("id"   ) var id   : String? = null,
  @SerializedName("name" ) var name : String? = null

)