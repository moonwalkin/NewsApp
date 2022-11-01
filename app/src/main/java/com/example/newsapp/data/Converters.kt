package com.example.newsapp.data

import androidx.room.TypeConverter
import com.example.newsapp.data.models.SourceData

class Converters {
    @TypeConverter
    fun toSource(source: String): SourceData {
        return SourceData(source, source)
    }

    @TypeConverter
    fun toString(sourceData: SourceData): String {
        return sourceData.name
    }
}