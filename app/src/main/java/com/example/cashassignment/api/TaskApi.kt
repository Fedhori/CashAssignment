package com.example.cashassignment.api

import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.enumclasses.TaskOrderStrategy
import com.example.cashassignment.model.BannerEntity
import com.example.cashassignment.model.TaskEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TaskApi {
    @GET("/task/list")
    suspend fun getTasks(
        @Query("page") page: Int,
        @Query("category") category: TaskCategory,
        @Query("orderStrategy") TaskOrderStrategy: TaskOrderStrategy
    ): Response<List<TaskEntity>>
}