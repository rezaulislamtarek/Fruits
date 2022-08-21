package com.diatomicsoft.fruits.view.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diatomicsoft.fruits.model.repository.FavRepository

class FavViewModelFactory(private val favRepository: FavRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavViewModel(favRepository) as T
    }
}

