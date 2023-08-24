package com.example.proyectofinal.UI.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.data.db.CotelesDatabase
import com.example.proyectofinal.data.retrofit.repository.CotelesRepository
import com.example.proyectofinal.model.Coteles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel  (application: Application): AndroidViewModel(application) {
    private val repository: CotelesRepository

    private val _favorites: MutableLiveData<List<Coteles>> = MutableLiveData()
    val favorites: LiveData<List<Coteles>> = _favorites

    init{
        val dao = CotelesDatabase.getDatabase(application).productDao()
        repository = CotelesRepository(dao)
    }

    fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO){
            val data = repository.getFavorite()
            data?.let {
                _favorites.postValue(it)
            }
        }
    }

}
