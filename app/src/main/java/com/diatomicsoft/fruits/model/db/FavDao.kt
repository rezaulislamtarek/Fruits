package com.diatomicsoft.fruits.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.diatomicsoft.fruits.network.responses.Fruit

@Dao
interface FavDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFav(fruit: Fruit)

    @Query("SELECT * FROM fav_table ORDER BY name")
    fun getAllFav() : LiveData<List<Fruit>>

    @Delete
    suspend fun delete(fruit: Fruit)
}