package com.example.cashassignment.api

import com.example.cashassignment.model.BundleEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BundleNotLoginApi {
    @GET("/task/not_login/bundles")
    suspend fun getBundles(
        @Query("country") country: String
    ): Response<List<BundleEntity>>
}