package com.example.tubearhe.retrofit

import com.example.tubearhe.model.Bear
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("beers")
    suspend fun getBears():List<Bear>


    companion object {
        var apiService:ApiService? = null

        fun getInstance() : ApiService{
            if (apiService==null){
                apiService= Retrofit.Builder()
                    .baseUrl("https://api.punkapi.com/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}