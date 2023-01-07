package com.example.domain.model


data class RoomData(
    val vacancyId:String,
    var vacancyName:String,
    var salary:String,
    var experience:String,
    var schedule:String,
    var employer:String,
    var area:String,
    var descriptions:String,
    var keySkills:List<String>?,
    var image:String?
)
