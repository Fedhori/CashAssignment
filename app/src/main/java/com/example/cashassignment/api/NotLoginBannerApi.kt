package com.example.cashassignment.api

import com.example.cashassignment.model.BannerEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface NotLoginBannerApi {
    @GET("/main/banner/not_login/list")
    fun getBanners(
        @Query("country") country: String
    ): retrofit2.Call<List<BannerEntity>>
}