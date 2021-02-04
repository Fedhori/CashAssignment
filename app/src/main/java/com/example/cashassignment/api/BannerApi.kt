package com.example.cashassignment.api

import com.example.cashassignment.enumclasses.AuthType
import com.example.cashassignment.model.BannerEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BannerApi {
    @GET("/main/banner/list")
    suspend fun getBanners(
        @Header("Authorization") token: String,
        @Header("AuthType") authType: AuthType
        //TODO implement query
    ): Response<List<BannerEntity>>
}
