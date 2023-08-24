package com.example.proyectofinal.data.retrofit


import com.example.proyectofinal.data.retrofit.response.CotelesListResponse
import retrofit2.Response
import retrofit2.http.GET

interface CotelesInterface {
    @GET("search.php?s=margarita")
    suspend fun getAllCoteles(): CotelesListResponse
}
