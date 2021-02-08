package com.example.cashassignment.model

import com.google.gson.annotations.SerializedName

data class UserDetailEntity (
    @SerializedName("currentPoint")
    val currentPoint: Int,

    @SerializedName("pendingPoint")
    val pendingPoint: Int,

    @SerializedName("totalPoint")
    val totalPoint: Int,

    @SerializedName("nickname")
    val nickname: String
)