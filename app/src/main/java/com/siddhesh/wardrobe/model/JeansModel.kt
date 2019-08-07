package com.siddhesh.wardrobe.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date



@Entity(tableName = "jeans")
class JeansModel :BaseModel(){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "j_id")
    var jeansId: Int = 0

    @ColumnInfo(name = "j_img_path")
    var image_path: String = ""

//    @ColumnInfo(name = "created_at")
//    var created_at: Date = Date(System.currentTimeMillis())
//
//    @ColumnInfo(name = "updated_at")
//    var updated_at: Date = Date(System.currentTimeMillis())
}