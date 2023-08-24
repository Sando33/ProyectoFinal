package com.example.proyectofinal.data.retrofit.repository

import com.example.proyectofinal.data.CotelesResult
import com.example.proyectofinal.data.db.CotelesDAO
import com.example.proyectofinal.data.retrofit.CotelesInterface
import com.example.proyectofinal.data.retrofit.RetrofitHelper
import com.example.proyectofinal.data.retrofit.response.CotelesListResponse
import com.example.proyectofinal.model.Coteles
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class CotelesRepository(val productoDao: CotelesDAO? = null) {

    suspend fun getCoteles() : CotelesResult<CotelesListResponse> {
        try{
            val response = RetrofitHelper.cotelesInstance.getAllCoteles()
            return CotelesResult.Success(response)
        } catch (e : Exception){
            return CotelesResult.Error(e)
        }
    }

    suspend fun getFavorite(): List<Coteles>? {
        return productoDao?.getFavorites()
    }

    suspend fun addToFovorite(product: Coteles){
        productoDao?.addFavorite(product)
    }

    suspend fun deleteFavorite(product: Coteles) {
        productoDao?.deleteFavorite(product)
    }

}