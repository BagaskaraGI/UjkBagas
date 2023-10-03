package com.example.ujkbagas.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ujkbagas.model.Menu
import com.example.ujkbagas.model.Pesanan

@Database(entities = [Menu::class, Pesanan::class], version = 1, exportSchema = false)
abstract class CafeDatabase : RoomDatabase() {

    abstract fun cafeDao(): CafeDao


    companion object {
        private var INSTANCE: CafeDatabase? = null
        fun getDatabase(context: Context): CafeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CafeDatabase::class.java,"menu_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}