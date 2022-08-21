package com.diatomicsoft.fruits.view.wallpaper.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.diatomicsoft.fruits.model.network.MyApi
import com.diatomicsoft.fruits.model.network.responses.Wallpaper

class WallpaperSource(private val api: MyApi) :PagingSource<Int, Wallpaper>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Wallpaper> {
        val page = params.key?: 1
        return try{
            val limit = params.loadSize
            val response = api.getWallpapers(page,limit)
            Log.d("wallpaper", response.body().toString())
            LoadResult.Page(
                data = response.body().orEmpty(),
                prevKey = if(page == 1) null else page - 1,
                nextKey = if (response.body()?.isEmpty() == true) null else page + 1
            )
        }catch (e : Exception){
            Log.d("Exception",e.toString())
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Wallpaper>): Int? {
        return 1
    }
}