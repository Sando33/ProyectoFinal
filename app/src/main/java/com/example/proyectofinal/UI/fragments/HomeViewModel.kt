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
    private val _coteles: MutableLiveData<List<Coteles>> = MutableLiveData<List<Coteles>>()
    val coteles: LiveData<List<Coteles>> = _coteles

    private val repository = CotelesRepository()

    fun getProductFromService() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.getCoteles()) {
                is CotelesResult.Success -> {
                    _coteles.postValue(response.data.coteles) // Cambio aquí para asignar la lista de coteles
                }
                is CotelesResult.Error -> {
                    // Manejo del error si es necesario
                }
            }
        }
    }
}