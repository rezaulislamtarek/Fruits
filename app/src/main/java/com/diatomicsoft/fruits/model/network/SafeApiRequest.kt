package com.diatomicsoft.fruits.model.network

import com.diatomicsoft.fruits.util.ApiExceptions
import retrofit2.Response

abstract class SafeApiRequest {
    suspend fun<T: Any> apiRequest(call: suspend ()-> Response<T>) : T{
        val response = call.invoke()
        if(response.isSuccessful) return response.body()!!
        else throw ApiExceptions(response.message()+"\nError Code "+response.code())
    }
    suspend fun<T: Any> apiRequestWithList(call: suspend () -> Response<List<T>>) : List<T> {
        val response = call.invoke()
        if(response.isSuccessful ) return response.body()!!
        else throw ApiExceptions(response.message()+"\nError Code "+response.code())
    }
}