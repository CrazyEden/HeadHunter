package com.example.data.reps

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.domain.model.RoomData
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "vacancies"
)
@TypeConverters(Converters::class)
@Parcelize
data class RoomDataEntity(
    @PrimaryKey @ColumnInfo(name = "vacancy_id") val vacancyId:String,
    @ColumnInfo(name = "vacancy_name") var vacancyName:String,
    @ColumnInfo(name = "salary") var salary:String,
    @ColumnInfo(name = "experience") var experience:String,
    @ColumnInfo(name = "schedule") var schedule:String,
    @ColumnInfo(name = "employer") var employer:String,
    @ColumnInfo(name = "area") var area:String,
    @ColumnInfo(name = "descriptions") var descriptions:String,
    @ColumnInfo(name = "key_skills") var keySkills:List<String>?,
    @ColumnInfo(name = "image") var image:String?
):Parcelable{
    fun toRoomData() = RoomData(
        vacancyId =vacancyId,
        vacancyName =vacancyName,
        salary =salary,
        experience =experience,
        schedule =schedule,
        employer =employer,
        area =area,
        descriptions =descriptions,
        keySkills =keySkills,
        image = image
    )
    companion object{
        fun fromRoomData(data:RoomData):RoomDataEntity {
            return RoomDataEntity(
                vacancyId = data.vacancyId,
                vacancyName = data.vacancyName,
                salary = data.salary,
                experience = data.experience,
                schedule = data.schedule,
                employer = data.employer,
                area = data.area,
                descriptions = data.descriptions,
                keySkills = data.keySkills,
                image = data.image)
        }
    }
}