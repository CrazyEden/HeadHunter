package com.example.headhunter.model.data.vacancyinfo

import com.google.gson.annotations.SerializedName


data class Contacts (

  @SerializedName("name"                  ) var name                : String?           = null,
  @SerializedName("email"                 ) var email               : String?           = null,
  @SerializedName("phones"                ) var phones              : ArrayList<Phones> = arrayListOf(),
  @SerializedName("call_tracking_enabled" ) var callTrackingEnabled : Boolean?          = null

)