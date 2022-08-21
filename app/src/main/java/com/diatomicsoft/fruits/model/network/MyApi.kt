package com.diatomicsoft.fruits.model.network

import com.diatomicsoft.fruits.network.responses.Fruit
import com.diatomicsoft.fruits.model.network.responses.Wallpaper
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MyApi {

    @GET("api/fruit/all")
    suspend fun allFruits() : Response<List<Fruit>>

    @GET("walpaper/all/{page}/{limit}")
    suspend fun getWallpapers(
        @Path("page") page: Int,
        @Path("limit") limit: Int
    ): Response<List<Wallpaper>>



    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): MyApi {
            val okHttpClient =
                OkHttpClient.Builder().addInterceptor(networkConnectionInterceptor).build()
            val retRofit = Retrofit.Builder().client(okHttpClient).baseUrl("https://iquote.diatomicsoft.com/api/")
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(MyApi::class.java)
            return retRofit
        }
    }
}