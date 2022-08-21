package com.diatomicsoft.fruits.view.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diatomicsoft.fruits.network.responses.Fruit
import com.diatomicsoft.fruits.model.repository.HomeRepository
import com.diatomicsoft.fruits.util.Coroutines.main

class HomeViewModel(homeRepository: HomeRepository) : ViewModel() {
    var fruitsList = MutableLiveData<List<Fruit>>()
    init {
        main{
            try {
                fruitsList.value = homeRepository.getAllFruits()
            }catch (e: Exception){
                Log.d("Error", e.message.toString())
            }
        }
    }
}