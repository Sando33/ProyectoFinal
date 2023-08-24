package com.example.proyectofinal.data.retrofit.repository

import com.example.proyectofinal.data.CotelesResult
import com.example.proyectofinal.data.retrofit.CotelesInterface
import com.example.proyectofinal.data.retrofit.RetrofitHelper
import com.example.proyectofinal.data.retrofit.response.CotelesListResponse
import com.example.proyectofinal.model.Coteles
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CotelesRepository(private val cotelesInterface: CotelesInterface) {

//    fun getCoteles(callback: (List<Coteles>?) -> Unit) {
//        cotelesInterface.getCoteles().enqueue(object : Callback<CotelesListResponse> {
//            override fun onResponse(call: retrofit2.Callback<CotelesListResponse>, response: Response<CotelesListResponse>) {
//                val cotelesResponse = response.body()
//                val coteles = cotelesResponse?.drinks
//                callback(coteles)
//            }
//
//            override fun onFailure(call: Call<CotelesListResponse>, t: Throwable) {
//                callback(null)
//            }
//        })
//    }
}