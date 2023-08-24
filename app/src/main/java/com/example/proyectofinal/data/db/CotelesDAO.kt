package com.example.proyectofinal.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectofinal.model.Coteles


@Dao
interface CotelesDAO {

    @Query("SELECT * FROM coteles")
    suspend fun getFavorites(): List<Coteles>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(coteles: Coteles)

    @Delete
    suspend fun deleteFavorite(coteles: Coteles)
}
