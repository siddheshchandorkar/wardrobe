package com.siddhesh.wardrobe.viewModel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.siddhesh.wardrobe.model.FavouriteModel
import com.siddhesh.wardrobe.model.JeansModel
import com.siddhesh.wardrobe.model.TopModel


@Database(entities = [TopModel::class, JeansModel::class, FavouriteModel::class], version = 1, exportSchema = false)
abstract class WardrobeRoomDB : RoomDatabase() {

    abstract fun daoAccess(): DaoAccess


    companion object {


        @Volatile
        private var INSTANCE: WardrobeRoomDB? = null

        fun getAppDatabase(context: Context): RoomDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    WardrobeRoomDB::class.java,
                    "wardrobe_database"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE as WardrobeRoomDB
        }
    }

}