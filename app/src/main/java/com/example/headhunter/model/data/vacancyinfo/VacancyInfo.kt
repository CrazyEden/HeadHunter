package com.example.headhunter.model.data.vacancyinfo

import com.google.gson.annotations.SerializedName


data class VacancyInfo (

  @SerializedName("id"                        ) var id                      : String?                         = null,
  @SerializedName("description"               ) var description             : String?                         = null,
  @SerializedName("branded_description"       ) var brandedDescription      : String?                         = null,
  @SerializedName("key_skills"                ) var keySkills               : ArrayList<KeySkills>            = arrayListOf(),
  @SerializedName("schedule"                  ) var schedule                : Schedule?                       = Schedule(),
  @SerializedName("accept_handicapped"        ) var acceptHandicapped       : Boolean?                        = null,
  @SerializedName("accept_kids"               ) var acceptKids              : Boolean?                        = null,
  @SerializedName("experience"                ) var experience              : Experience?                     = Experience(),
  @SerializedName("address"                   ) var address                 : Address?                        = Address(),
  @SerializedName("alternate_url"             ) var alternateUrl            : String?                         = null,
  @SerializedName("apply_alternate_url"       ) var applyAlternateUrl       : String?                         = null,
  @SerializedName("code"                      ) var code                    : String?                         = null,
  @SerializedName("department"                ) var department              : Department?                     = Department(),
  @SerializedName("employment"                ) var employment              : Employment?                     = Employment(),
  @SerializedName("salary"                    ) var salary                  : Salary?                         = Salary(),
  @SerializedName("archived"                  ) var archived                : Boolean?                        = null,
  @SerializedName("name"                      ) var name                    : String?                         = null,
  @SerializedName("insider_interview"         ) var insiderInterview        : InsiderInterview?               = InsiderInterview(),
  @SerializedName("area"                      ) var area                    : Area?                           = Area(),
  @SerializedName("initial_created_at"        ) var initialCreatedAt        : String?                         = null,
  @SerializedName("created_at"                ) var createdAt               : String?                         = null,
  @SerializedName("published_at"              ) var publishedAt             : String?                         = null,
  @SerializedName("employer"                  ) var employer                : Employer?                       = Employer(),
  @SerializedName("response_letter_required"  ) var responseLetterRequired  : Boolean?                        = null,
  @SerializedName("type"                      ) var type                    : Type?                           = Type(),
  @SerializedName("has_test"                  ) var hasTest                 : Boolean?                        = null,
  @SerializedName("response_url"              ) var responseUrl             : String?                         = null,
  @SerializedName("test"                      ) var test                    : Test?                           = Test(),
  @SerializedName("contacts"                  ) var contacts                : Contacts?                       = Contacts(),
  @SerializedName("billing_type"              ) var billingType             : BillingType?                    = BillingType(),
  @SerializedName("allow_messages"            ) var allowMessages           : Boolean?                        = null,
  @SerializedName("premium"                   ) var premium                 : Boolean?                        = null,
  @SerializedName("driver_license_types"      ) var driverLicenseTypes      : ArrayList<DriverLicenseTypes>   = arrayListOf(),
  @SerializedName("accept_incomplete_resumes" ) var acceptIncompleteResumes : Boolean?                        = null,
  @SerializedName("working_days"              ) var workingDays             : ArrayList<WorkingDays>          = arrayListOf(),
  @SerializedName("working_time_intervals"    ) var workingTimeIntervals    : ArrayList<WorkingTimeIntervals> = arrayListOf(),
  @SerializedName("working_time_modes"        ) var workingTimeModes        : ArrayList<WorkingTimeModes>     = arrayListOf(),
  @SerializedName("accept_temporary"          ) var acceptTemporary         : Boolean?                        = null,
  @SerializedName("professional_roles"        ) var professionalRoles       : ArrayList<ProfessionalRoles>    = arrayListOf(),
  @SerializedName("languages"                 ) var languages               : ArrayList<Languages>            = arrayListOf()

)