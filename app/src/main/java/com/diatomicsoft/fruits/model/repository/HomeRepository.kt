package com.diatomicsoft.fruits.model.repository

import com.diatomicsoft.fruits.model.network.MyApi
import com.diatomicsoft.fruits.model.network.SafeApiRequest
import com.diatomicsoft.fruits.network.responses.Fruit

class HomeRepository(private val api: MyApi): SafeApiRequest() {

    suspend fun getAllFruits(): List<Fruit> {
        return apiRequestWithList { api.allFruits() }
    }



}

/* return try {
            var response = api.allFruits()
            response.body()!!
        }catch (e: ApiExceptions){
            throw ApiExceptions(e.message.toString())
        }catch (e: Exception){
            throw java.lang.Exception(e.message)
        }*/