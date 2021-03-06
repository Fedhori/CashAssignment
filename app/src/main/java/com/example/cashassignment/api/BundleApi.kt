package com.example.cashassignment.api

import com.example.cashassignment.enumclasses.AuthType
import com.example.cashassignment.model.BundleEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BundleApi {
    @GET("/task/bundles")
    suspend fun getBundles(
        @Header("Authorization") token: String,
        @Header("AuthType") authType: AuthType
    ): Response<List<BundleEntity>>
}