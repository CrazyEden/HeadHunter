package com.example.data.reps

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.RoomData

@Entity(
    tableName = "vacancies"
)
data class RoomDataEntity(
    @PrimaryKey @ColumnInfo(name = "vacancy_id") val vacancyId:String,
    @ColumnInfo(name = "vacancy_name") var vacancyName:String,
    @ColumnInfo(name = "salary") var salary:String,
    @ColumnInfo(name = "experience") var experience:String,
    @ColumnInfo(name = "schedule") var schedule:String,
    @ColumnInfo(name = "employer") var employer:String,
    @ColumnInfo(name = "area") var area:String,
    @ColumnInfo(name = "descriptions") var descriptions:String,
    @ColumnInfo(name = "key_skills") var keySkills:String
){
    fun toRoomData() = RoomData(
        vacancyId =vacancyId,
        vacancyName =vacancyName,
        salary =salary,
        experience =experience,
        schedule =schedule,
        employer =employer,
        area =area,
        descriptions =descriptions,
        keySkills =keySkills
    )
}