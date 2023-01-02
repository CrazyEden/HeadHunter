package com.example.headhunter.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PagerDataParams(
    var text: String? = "junior android",
    var experienceId:String? = null,
    var employmentIds:MutableList<String?> = mutableListOf(null,null,null,null,null),
    var scheduleIds:MutableList<String?> = mutableListOf(null,null,null,null,null),
    var salary:Int? = null,
    var onlyWithSalary:Boolean = false
):Parcelable
