package com.example.headhunter.model.data

import com.google.gson.annotations.SerializedName


data class Items (

  @SerializedName("salary"                   ) var salary                 : Salary?           = Salary(),
  @SerializedName("name"                     ) var name                   : String?           = null,
  @SerializedName("insider_interview"        ) var insiderInterview       : InsiderInterview? = InsiderInterview(),
  @SerializedName("area"                     ) var area                   : Area?             = Area(),
  @SerializedName("url"                      ) var url                    : String?           = null,
  @SerializedName("published_at"             ) var publishedAt            : String?           = null,
  @SerializedName("relations"                ) var relations              : ArrayList<String> = arrayListOf(),
  @SerializedName("employer"                 ) var employer               : Employer?         = Employer(),
  @SerializedName("contacts"                 ) var contacts               : Contacts?         = Contacts(),
  @SerializedName("response_letter_required" ) var responseLetterRequired : Boolean?          = null,
  @SerializedName("address"                  ) var address                : Address?          = Address(),
  @SerializedName("sort_point_distance"      ) var sortPointDistance      : Double?           = null,
  @SerializedName("alternate_url"            ) var alternateUrl           : String?           = null,
  @SerializedName("apply_alternate_url"      ) var applyAlternateUrl      : String?           = null,
  @SerializedName("department"               ) var department             : Department?       = Department(),
  @SerializedName("type"                     ) var type                   : Type?             = Type(),
  @SerializedName("id"                       ) var id                     : String?           = null,
  @SerializedName("has_test"                 ) var hasTest                : Boolean?          = null,
  @SerializedName("response_url"             ) var responseUrl            : String?           = null,
  @SerializedName("snippet"                  ) var snippet                : Snippet?          = Snippet(),
  @SerializedName("schedule"                 ) var schedule               : Schedule?         = Schedule(),
  @SerializedName("counters"                 ) var counters               : Counters?         = Counters()

)