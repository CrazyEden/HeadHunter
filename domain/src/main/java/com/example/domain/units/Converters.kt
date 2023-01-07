package com.example.domain.units

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converters {

    @TypeConverter
    fun fromList(list:List<String>?):String{
        if (list.isNullOrEmpty()) return ""
        return list.joinToString(",")
    }
    @TypeConverter
    fun toList(string: String):List<String>{
        return string.split(",")
    }

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray{
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
        return stream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(array: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(array, 0, array.size)
    }
}