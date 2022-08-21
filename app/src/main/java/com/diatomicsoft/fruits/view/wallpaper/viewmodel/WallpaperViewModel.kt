package com.diatomicsoft.fruits.view.wallpaper.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.diatomicsoft.fruits.model.network.MyApi
import com.diatomicsoft.fruits.model.network.responses.Wallpaper
import com.diatomicsoft.fruits.view.wallpaper.repository.WallpaperSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WallpaperViewModel(private val api: MyApi) : ViewModel() {
    //var allWallpapers: Flow<PagingData<Wallpaper>>

    fun getAllWallPaper() = Pager(
        config = PagingConfig(10, prefetchDistance = 2),
        pagingSourceFactory = { WallpaperSource(api) }).flow.cachedIn(viewModelScope)

}

//Flow<PagingData<Wallpapers>>