package com.example.domain.model.vacancyinfo

import com.google.gson.annotations.SerializedName


data class Salary (

  @SerializedName("to"       ) var to       : Int?  = null,
  @SerializedName("from"     ) var from     : Int?     = null,
  @SerializedName("currency" ) var currency : String?  = null,
  @SerializedName("gross"    ) var gross    : Boolean? = null

)