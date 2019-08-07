package com.siddhesh.wardrobe.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.siddhesh.wardrobe.model.FavouriteModel
import com.siddhesh.wardrobe.model.JeansModel
import com.siddhesh.wardrobe.model.TopModel


class WardrobeViewModel(application: Application) : AndroidViewModel(application) {


    private var repository: ClothRepository


    init {
        val movieDao: WardrobeRoomDB = WardrobeRoomDB.getAppDatabase(application) as WardrobeRoomDB
        repository = ClothRepository(movieDao.daoAccess())


    }

    fun addTop(topModel: TopModel) {
        repository.insertTop(topModel)
    }

    fun addJeans(jeansModel: JeansModel) {
        repository.insertJeans(jeansModel)
    }

    fun selectFavCombo(favouriteModel: FavouriteModel) {
        repository.insertFavourite(favouriteModel)
    }

    fun getTops(): ArrayList<TopModel> {
        return repository.getAllTops()
    }

    fun getJeans() :ArrayList<JeansModel>{
        return repository.getAllJeans()
    }

    fun getFavCombo() :ArrayList<FavouriteModel>{

        return repository.getAllFav()
    }


}