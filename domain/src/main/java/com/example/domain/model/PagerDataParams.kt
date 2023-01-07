package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PagerDataParamsParcel(
    var text: String? = null,
    var experienceId:String? = null,
    var employmentIds:MutableList<String?> = mutableListOf(null,null,null,null,null),
    var scheduleIds:MutableList<String?> = mutableListOf(null,null,null,null,null),
    var salary:Int? = null,
    var onlyWithSalary:Boolean = false
): Parcelable