package com.example.headhunter.model.data

import com.google.gson.annotations.SerializedName


data class Snippet (

  @SerializedName("requirement"    ) var requirement    : String? = null,
  @SerializedName("responsibility" ) var responsibility : String? = null

)