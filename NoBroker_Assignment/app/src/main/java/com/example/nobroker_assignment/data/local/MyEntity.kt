package com.example.nobroker_assignment.data.local

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * here we specify the name of row as preference
 */
@Entity(tableName = "my_nobroker_table")
data class MyEntity(
    @ColumnInfo(name = "image") val image: Bitmap,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "subTitle") val subTitle: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}