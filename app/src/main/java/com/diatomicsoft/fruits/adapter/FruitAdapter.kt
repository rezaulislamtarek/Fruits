package com.diatomicsoft.fruits.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.diatomicsoft.fruits.R
import com.diatomicsoft.fruits.databinding.RecyclerviewFruitsBinding
import com.diatomicsoft.fruits.network.responses.Fruit

class FruitAdapter(val fruits: List<Fruit>, val listener: RecyclerViewListener) : RecyclerView.Adapter<FruitAdapter.FruitViewHolder>()  {

    inner class FruitViewHolder(
        val recyclerviewFruitsBinding: RecyclerviewFruitsBinding
    ) : RecyclerView.ViewHolder(recyclerviewFruitsBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        return FruitViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_fruits,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        holder.recyclerviewFruitsBinding.fruits = fruits[position]
        holder.recyclerviewFruitsBinding.cv.setOnClickListener{
            listener.onRecyclerViewItemClick(it,fruits[position])
        }
    }

    override fun getItemCount(): Int {
        return fruits.size
    }
}