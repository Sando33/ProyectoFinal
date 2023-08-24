package com.example.proyectofinal.UI.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.data.CotelesResult
import com.example.proyectofinal.data.retrofit.CotelesInterface
import com.example.proyectofinal.data.retrofit.RetrofitHelper
import com.example.proyectofinal.data.retrofit.repository.CotelesRepository
import com.example.proyectofinal.model.Coteles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val apiService = RetrofitHelper.createService(CotelesInterface::class.java)
    private val _cocktailList = MutableLiveData<List<Coteles>>()
    val cocktailList: LiveData<List<Coteles>> get() = _cocktailList

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val response = apiService.getCoteles()
                if (response.isSuccessful) {
                    _cocktailList.postValue(response.body()?.drinks)
                }
            } catch (e: Exception) {
                // Manejar errores aquí
            }
        }
    }
}