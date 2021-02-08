package com.example.cashassignment.api

import com.example.cashassignment.enumclasses.AuthType
import com.example.cashassignment.model.BannerEntity
import com.example.cashassignment.model.UserDetailEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserDetailApi {
    @GET("/main/user/detail")
    suspend fun getUserDetail(
        @Header("Authorization") token: String,
        @Header("AuthType") authType: AuthType
        //TODO implement query
    ): Response<UserDetailEntity>
}