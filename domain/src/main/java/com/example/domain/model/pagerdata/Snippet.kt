package com.example.domain.model.pagerdata

import com.google.gson.annotations.SerializedName


data class Snippet (

  @SerializedName("requirement"    ) var requirement    : String? = null,
  @SerializedName("responsibility" ) var responsibility : String? = null

)