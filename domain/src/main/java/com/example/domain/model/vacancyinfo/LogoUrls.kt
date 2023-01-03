package com.example.domain.model.vacancyinfo

import com.google.gson.annotations.SerializedName


data class LogoUrls (

  @SerializedName("90"       ) var _90       : String? = null,
  @SerializedName("240"      ) var _240      : String? = null,
  @SerializedName("original" ) var original : String? = null

)