package com.example.proyectofinal.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "coteles")
@Parcelize
data class Coteles(


    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String,
    val strCategory: String,

    @SerializedName("is_favorite")
    var isFavorite: Boolean = false,



    ): Parcelable
