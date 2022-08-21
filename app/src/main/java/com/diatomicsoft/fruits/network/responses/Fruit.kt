package com.diatomicsoft.fruits.network.responses

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "fav_table")
data class Fruit(
    val genus: String,
    val name: String,
    @PrimaryKey val id: Int,
    val family: String,
    val order: String,
) :  Parcelable
