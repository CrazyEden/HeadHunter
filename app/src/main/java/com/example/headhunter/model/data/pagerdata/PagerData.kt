package com.example.headhunter.model.data.pagerdata

import com.google.gson.annotations.SerializedName


data class PagerData (

    @SerializedName("per_page"  ) var perPage   : Int?             = null,
    @SerializedName("items"     ) var items     : ArrayList<Items> = arrayListOf(),
    @SerializedName("page"      ) var page      : Int?             = null,
    @SerializedName("pages"     ) var pages     : Int?             = null,
    @SerializedName("found"     ) var found     : Int?             = null,
    @SerializedName("clusters"  ) var clusters  : String?          = null,
    @SerializedName("arguments" ) var arguments : String?          = null

)