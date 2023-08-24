package com.example.proyectofinal.data.retrofit.response

import com.example.proyectofinal.model.Coteles
import com.google.gson.annotations.SerializedName

data class CotelesListResponse(
    @SerializedName("drinks") // Agrega esta anotación para mapear la lista de objetos en el JSON
    val coteles: List<Coteles>
)