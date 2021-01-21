package com.example.cashassignment.api

import com.example.cashassignment.model.BannerEntity
import retrofit2.http.GET

interface TaskApi {
    @GET("/main/task/list")
    fun getBanners(
        //TODO implement query
    ): retrofit2.Call<List<BannerEntity>>
}