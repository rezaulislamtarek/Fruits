package com.diatomicsoft.fruits.socket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.diatomicsoft.fruits.R
import com.diatomicsoft.fruits.databinding.RecyclerviewSocketBinding


class UserAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(
        val recyclerviewSocketBinding: RecyclerviewSocketBinding
    ) : RecyclerView.ViewHolder(recyclerviewSocketBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val vh = UserViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_socket,
                parent,
                false
            )
        )
        return vh
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.recyclerviewSocketBinding.user = users[position]
    }

    override fun getItemCount(): Int {
        return users.size
    }
}