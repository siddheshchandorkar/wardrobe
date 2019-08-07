package com.siddhesh.wardrobe.model

import androidx.room.ColumnInfo

 open class BaseModel{

    @ColumnInfo(name = "created_at")
    var created_at: String = ""


}
