package com.example.domain.model

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.domain.units.Converters
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
    @ColumnInfo(name = "image") var image:Bitmap?
): Parcelable
