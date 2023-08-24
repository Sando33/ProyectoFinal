package com.example.proyectofinal.UI.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.data.db.CotelesDatabase
import com.example.proyectofinal.data.retrofit.repository.CotelesRepository
import com.example.proyectofinal.model.Coteles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CotelesDetailViewModel  (application: Application): AndroidViewModel(application) {
    private val repository: CotelesRepository

    init{
        val dao = CotelesDatabase.getDatabase(application).productDao()
        repository = CotelesRepository(dao)
    }

    fun addToFovorite(coteles: Coteles){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToFovorite(coteles)
        }
    }

    fun deleteFromFavorite(coteles: Coteles) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(coteles)
        }
    }
}