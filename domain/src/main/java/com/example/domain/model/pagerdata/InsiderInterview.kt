package com.example.domain.model.pagerdata

import com.google.gson.annotations.SerializedName


data class InsiderInterview (

  @SerializedName("id"  ) var id  : String? = null,
  @SerializedName("url" ) var url : String? = null

)