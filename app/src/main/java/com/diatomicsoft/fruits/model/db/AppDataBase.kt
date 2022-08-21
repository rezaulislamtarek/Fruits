package com.diatomicsoft.fruits.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diatomicsoft.fruits.network.responses.Fruit

@Database(entities = arrayOf(Fruit::class), version = 1, exportSchema = false)
public abstract class AppDataBase : RoomDatabase() {
    abstract fun favDao(): FavDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var instance: AppDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "AppDB.db"
            )
                .build()


    }
}