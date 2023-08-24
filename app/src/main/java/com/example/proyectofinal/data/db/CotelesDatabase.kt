package com.example.proyectofinal.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectofinal.model.Coteles


@Database(entities = [Coteles::class], version = 1)
abstract class CotelesDatabase: RoomDatabase() {

    abstract fun productDao(): CotelesDAO

    companion object{
        private var instance: CotelesDatabase?=null

        fun getDatabase(context: Context): CotelesDatabase{
            val temp = instance
            if(temp != null){
                return temp
            }
            synchronized(this){
                val _instance = Room.databaseBuilder(
                    context.applicationContext,
                    CotelesDatabase::class.java,
                    "cotelesdb"
                ).build()
                instance = _instance
                return _instance
            }
        }
    }
}