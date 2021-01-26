package com.example.cashassignment.api

import com.example.cashassignment.model.BannerEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BannerNotLoginApi {
    @GET("/main/banner/not_login/list")
    suspend fun getBanners(
        @Query("country") country: String
    ): Response<List<BannerEntity>>
}