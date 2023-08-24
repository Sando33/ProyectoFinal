package com.example.proyectofinal.data

import java.lang.Exception

sealed class CotelesResult<T>(data : T? = null, error: Exception? = null){
    data class Success<T>(val data: T) : CotelesResult<T>(data, null)
    data class Error<T>(val error : Exception): CotelesResult<T>(null, error)
}
