package com.example.domain.model.vacancyinfo

import com.google.gson.annotations.SerializedName


data class MetroStations (

  @SerializedName("station_id"   ) var stationId   : String? = null,
  @SerializedName("station_name" ) var stationName : String? = null,
  @SerializedName("line_id"      ) var lineId      : String? = null,
  @SerializedName("line_name"    ) var lineName    : String? = null,
  @SerializedName("lat"          ) var lat         : Double? = null,
  @SerializedName("lng"          ) var lng         : Double? = null

)