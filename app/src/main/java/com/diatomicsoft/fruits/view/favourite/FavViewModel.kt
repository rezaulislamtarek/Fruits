package com.diatomicsoft.fruits.view.favourite

import androidx.lifecycle.ViewModel
import com.diatomicsoft.fruits.model.repository.FavRepository

class FavViewModel(private val favRepository: FavRepository) : ViewModel() {

    fun getAllFavFruits() = favRepository.getAllFavFruits()


}