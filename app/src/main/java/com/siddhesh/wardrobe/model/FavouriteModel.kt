package com.siddhesh.wardrobe.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.siddhesh.wardrobe.utils.TimestampConverter
import java.sql.Date


@Entity(tableName = "favourite")
class FavouriteModel:BaseModel() {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "f_id")
    var favId: Int = 0

    @ColumnInfo(name = "t_id" )
    var topId: Int = 0

    @ColumnInfo(name = "j_id" )
    var jeansId: Int = 0

    override fun toString(): String {
        return "FavouriteModel(favId=$favId, topId=$topId, jeansId=$jeansId)"
    }


}