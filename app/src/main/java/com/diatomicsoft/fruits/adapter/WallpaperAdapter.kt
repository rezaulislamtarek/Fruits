package com.diatomicsoft.fruits.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diatomicsoft.fruits.databinding.RecyclerviewWallpaperBinding
import com.diatomicsoft.fruits.model.network.responses.Wallpaper

class WallpaperAdapter : PagingDataAdapter<Wallpaper, WallpaperAdapter.WallpaperViewHolder>(Diff) {

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolderCall()")
        val wallpaper = getItem(position)
        holder.binds(wallpaper!!)

        Log.d("Adapter", "onBindViewHolder: ${wallpaper.walpaper_url}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        Log.d("Adapter", "onCreateViewHolderCall()")
        return WallpaperViewHolder(
            RecyclerviewWallpaperBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class WallpaperViewHolder(private val wallpaperBinding: RecyclerviewWallpaperBinding) :
        RecyclerView.ViewHolder(wallpaperBinding.root) {
        fun binds(wallpaper: Wallpaper) {
            wallpaperBinding.wallpaper = wallpaper
        }
    }


    object Diff : DiffUtil.ItemCallback<Wallpaper>() {
        override fun areItemsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean =
            oldItem.walpaper_name == newItem.walpaper_name
    }
}