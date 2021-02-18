package com.example.cashassignment.api

import com.example.cashassignment.enumclasses.AuthType
import com.example.cashassignment.enumclasses.Statuses
import retrofit2.http.*

interface DibsApi {
    @POST("/task/dibs")
    suspend fun postDibs(
        @Header("Authorization") token: String,
        @Header("AuthType") authType: AuthType,
        @Query("taskId") taskId: Long
    )

    @DELETE("/task/dibs")
    suspend fun deleteDibs(
        @Header("Authorization") token: String,
        @Header("AuthType") authType: AuthType,
        @Query("taskId") taskId: Long
    )

    @GET("/v2/task/dibs/list")
    suspend fun getDibsList(
        @Header("Authorization") token: String,
        @Header("AuthType") authType: AuthType,
        @Query("page") page: Int,
        @Query("statuses") statuses: Statuses
    )
}