package com.siddhesh.wardrobe.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "top")
class TopModel : BaseModel() {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "t_id")
    var topId: Int = 0

    @ColumnInfo(name = "t_img_path")
    var image_path: String = ""

    override fun toString(): String {
        return "TopModel(topId=$topId, image_path='$image_path')"
    }

//    @ColumnInfo(name = "created_at")
//    var created_at: Date = Date(System.currentTimeMillis())
//
//    @ColumnInfo(name = "updated_at")
//    var updated_at: Date = Date(System.currentTimeMillis())



}