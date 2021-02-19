package com.example.cashassignment.api

import com.example.cashassignment.enumclasses.AuthType
import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.enumclasses.TaskOrderStrategy
import com.example.cashassignment.model.BannerEntity
import com.example.cashassignment.model.TaskEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TaskApi {
    @GET("/task/list")
    suspend fun getTasks(
        @Header("Authorization") token: String,
        @Header("AuthType") authType: AuthType,
        @Query("page") page: Int,
        @Query("category") category: TaskCategory,
        @Query("orderStrategy") taskOrderStrategy: TaskOrderStrategy
    ): Response<List<TaskEntity>>

    @GET("/task/recent/list")
    suspend fun getRecentPageData(
        @Header("Authorization") token: String,
        @Header("AuthType") authType: AuthType,
        @Query("page") page: Int
    ): Response<List<TaskEntity>>
}