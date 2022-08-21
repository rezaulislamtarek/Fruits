package com.diatomicsoft.fruits.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.diatomicsoft.fruits.model.db.FavDao
import com.diatomicsoft.fruits.network.responses.Fruit

class FavRepository(private val favDao: FavDao) {

    suspend fun addToFav(fruit: Fruit){
        favDao.addFav(fruit)
        Log.d("addData", fruit.toString())
    }


     fun getAllFavFruits():  LiveData<List<Fruit>> {
        Log.d("getData", favDao.getAllFav().toString())
        return favDao.getAllFav()
    }

    suspend fun deleteFav(fruit: Fruit) = favDao.delete(fruit)
}