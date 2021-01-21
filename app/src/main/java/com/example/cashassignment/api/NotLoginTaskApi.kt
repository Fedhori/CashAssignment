package com.example.cashassignment.api

import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.enumclasses.TaskOrderStrategy
import com.example.cashassignment.model.TaskEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface NotLoginTaskApi {
    @GET("/task/not_login/list")
    fun getTasks(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("category") category: TaskCategory,
        @Query("orderStrategy") orderStrategy: TaskOrderStrategy
    ): retrofit2.Call<List<TaskEntity>>
}