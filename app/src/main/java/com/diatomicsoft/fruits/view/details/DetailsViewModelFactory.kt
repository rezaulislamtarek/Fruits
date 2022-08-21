package com.diatomicsoft.fruits.view.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diatomicsoft.fruits.model.repository.FavRepository

class DetailsViewModelFactory(private val favRepository: FavRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(favRepository) as T
    }
}

//*class HomeViewModelFactory(private val homeRepository: HomeRepository): ViewModelProvider.NewInstanceFactory() {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return HomeViewModel(homeRepository) as T
//    }
//}*/