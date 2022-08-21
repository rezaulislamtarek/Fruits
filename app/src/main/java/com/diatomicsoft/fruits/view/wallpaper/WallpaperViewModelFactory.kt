package com.diatomicsoft.fruits.view.wallpaper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diatomicsoft.fruits.model.network.MyApi
import com.diatomicsoft.fruits.view.wallpaper.viewmodel.WallpaperViewModel

class WallpaperViewModelFactory(private val api: MyApi) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WallpaperViewModel(api) as T
    }
}