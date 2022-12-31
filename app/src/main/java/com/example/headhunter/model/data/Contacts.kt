package com.example.headhunter.model.data

import com.google.gson.annotations.SerializedName


data class Contacts (

  @SerializedName("name"   ) var name   : String?           = null,
  @SerializedName("email"  ) var email  : String?           = null,
  @SerializedName("phones" ) var phones : ArrayList<Phones> = arrayListOf()

)