package com.example.cashassignment.api

import com.example.cashassignment.enumclasses.AuthType
import com.example.cashassignment.model.PrivacyDetailEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface PrivacyDetailApi {
    @GET("/privacy/detail")
    suspend fun getUserDetail(
        @Header("Authorization") token: String,
        @Header("AuthType") authType: AuthType
        //TODO implement query
    ): Response<PrivacyDetailEntity>
}