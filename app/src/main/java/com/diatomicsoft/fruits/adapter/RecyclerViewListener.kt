package com.diatomicsoft.fruits.adapter

import android.view.View
import com.diatomicsoft.fruits.network.responses.Fruit

interface RecyclerViewListener {
    fun onRecyclerViewItemClick(view: View, fruit: Fruit)
}