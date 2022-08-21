package com.diatomicsoft.fruits.view.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diatomicsoft.fruits.network.responses.Fruit
import com.diatomicsoft.fruits.model.repository.FavRepository
import kotlinx.coroutines.launch

class DetailsViewModel(private val favRepository: FavRepository): ViewModel() {
    fun insert(fruit: Fruit) = viewModelScope.launch {
        favRepository.addToFav(fruit)
    }
}