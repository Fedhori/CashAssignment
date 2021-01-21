package com.example.cashassignment.api

import com.example.cashassignment.model.BannerEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface BannerApi {
    @GET("/main/banner/list")
    fun getBanners(
        //TODO implement query
    ): retrofit2.Call<List<BannerEntity>>
}
