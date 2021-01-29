package com.example.cashassignment

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseService {
    private const val BASE_URL_API = "https://dev-v2.selectstar.co.kr/"
    private var instance : Retrofit? = null

    fun getInstance() : Retrofit{
        if(instance == null){
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }
}