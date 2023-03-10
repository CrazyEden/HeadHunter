package com.example.domain.model.pagerdata

import com.google.gson.annotations.SerializedName


data class Phones (

  @SerializedName("country" ) var country : String? = null,
  @SerializedName("city"    ) var city    : String? = null,
  @SerializedName("number"  ) var number  : String? = null,
  @SerializedName("comment" ) var comment : String? = null

)