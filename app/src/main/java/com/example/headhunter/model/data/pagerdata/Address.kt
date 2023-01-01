package com.example.headhunter.model.data.pagerdata

import com.google.gson.annotations.SerializedName


data class Address (

  @SerializedName("city"           ) var city          : String?                  = null,
  @SerializedName("street"         ) var street        : String?                  = null,
  @SerializedName("building"       ) var building      : String?                  = null,
  @SerializedName("description"    ) var description   : String?                  = null,
  @SerializedName("lat"            ) var lat           : Double?                  = null,
  @SerializedName("lng"            ) var lng           : Double?                  = null,
  @SerializedName("metro_stations" ) var metroStations : ArrayList<MetroStations> = arrayListOf()

)