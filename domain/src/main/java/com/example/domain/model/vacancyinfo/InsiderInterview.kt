package com.example.domain.model.vacancyinfo

import com.google.gson.annotations.SerializedName


data class InsiderInterview (

  @SerializedName("id"  ) var id  : String? = null,
  @SerializedName("url" ) var url : String? = null

)