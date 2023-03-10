package com.example.domain.model.vacancyinfo

import com.google.gson.annotations.SerializedName


data class Employer (

    @SerializedName("logo_urls"          ) var logoUrls          : LogoUrls?          = LogoUrls(),
    @SerializedName("name"               ) var name              : String?            = null,
    @SerializedName("url"                ) var url               : String?            = null,
    @SerializedName("alternate_url"      ) var alternateUrl      : String?            = null,
    @SerializedName("id"                 ) var id                : String?            = null,
    @SerializedName("trusted"            ) var trusted           : Boolean?           = null,
    @SerializedName("blacklisted"        ) var blacklisted       : Boolean?           = null,
    @SerializedName("applicant_services" ) var applicantServices : ApplicantServices? = ApplicantServices()

)