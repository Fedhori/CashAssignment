package com.example.cashassignment.api

import com.example.cashassignment.enumclasses.AuthType
import com.example.cashassignment.enumclasses.Statuses
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface DibsApi {
    @POST("/task/dibs")
    suspend fun postDibs(
        @Header("Authorization") token: Long,
        @Header("AuthType") authType: AuthType,
        @Query("taskId") taskId: String
    )

    @POST("/delete/dibs")
    suspend fun deleteDibs(
        @Header("Authorization") token: Long,
        @Header("AuthType") authType: AuthType,
        @Query("taskId") taskId: String
    )

    @GET("/v2/task/dibs/list")
    suspend fun getDibsList(
        @Header("Authorization") token: Long,
        @Header("AuthType") authType: AuthType,
        @Query("page") page: Int,
        @Query("statuses") statuses: Statuses
    )
}