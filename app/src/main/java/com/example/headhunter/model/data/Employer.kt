package com.example.headhunter.model.data

import com.google.gson.annotations.SerializedName


data class Employer (

  @SerializedName("logo_urls"     ) var logoUrls     : LogoUrls? = LogoUrls(),
  @SerializedName("name"          ) var name         : String?   = null,
  @SerializedName("url"           ) var url          : String?   = null,
  @SerializedName("alternate_url" ) var alternateUrl : String?   = null,
  @SerializedName("id"            ) var id           : String?   = null,
  @SerializedName("trusted"       ) var trusted      : Boolean?  = null

)