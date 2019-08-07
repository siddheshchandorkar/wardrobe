package com.siddhesh.wardrobe.viewModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.siddhesh.wardrobe.model.FavouriteModel
import com.siddhesh.wardrobe.model.JeansModel
import com.siddhesh.wardrobe.model.TopModel


@Dao
interface DaoAccess {

    @Insert
    fun insertTop(topModel: TopModel): Long

    @Insert
    fun insertJeans(jeansModel: JeansModel): Long

    @Insert
    fun insertFav(favouriteModel: FavouriteModel): Long


    @Query("SELECT * FROM top ")
    fun fetchAllTops(): List<TopModel>

    @Query("SELECT * FROM jeans ")
    fun fetchAllJeans(): List<JeansModel>

    @Query("SELECT * FROM favourite ")
    fun fetchAllFavourite(): List<FavouriteModel>


}