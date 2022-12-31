package com.example.headhunter.model.data

import com.google.gson.annotations.SerializedName


data class Area (

  @SerializedName("url"  ) var url  : String? = null,
  @SerializedName("id"   ) var id   : String? = null,
  @SerializedName("name" ) var name : String? = null

)