package com.example.domain.model.pagerdata

import com.google.gson.annotations.SerializedName


data class Schedule (

  @SerializedName("id"   ) var id   : String? = null,
  @SerializedName("name" ) var name : String? = null

)