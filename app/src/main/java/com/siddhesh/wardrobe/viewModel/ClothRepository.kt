package com.siddhesh.wardrobe.viewModel

import com.siddhesh.wardrobe.model.FavouriteModel
import com.siddhesh.wardrobe.model.JeansModel
import com.siddhesh.wardrobe.model.TopModel


class ClothRepository(private var daoAccess: DaoAccess) {

    var alTop: ArrayList<TopModel> = ArrayList()
    var alJeans: ArrayList<JeansModel> = ArrayList()
    var alFavMovie: ArrayList<FavouriteModel> = ArrayList()


    fun insertTop(topModel: TopModel) {
        daoAccess.insertTop(topModel)

    }

    fun insertJeans(jeansModel: JeansModel) {
        daoAccess.insertJeans(jeansModel)

    }

    fun insertFavourite(favouriteModel: FavouriteModel): Boolean {
        if(daoAccess.checkFavCombo(favouriteModel.topId, favouriteModel.jeansId)>0){
            daoAccess.insertFav(favouriteModel)
            return true
        }else
            return false

    }


    fun getAllTops() : ArrayList<TopModel> {
        alTop = daoAccess.fetchAllTops() as ArrayList<TopModel>

        return alTop

    }

    fun getAllJeans() : ArrayList<JeansModel>{
        alJeans = daoAccess.fetchAllJeans() as ArrayList<JeansModel>

        return  alJeans

    }

    fun getAllFav(): ArrayList<FavouriteModel> {
        alFavMovie = daoAccess.fetchAllFavourite() as ArrayList<FavouriteModel>

        return alFavMovie

    }


}