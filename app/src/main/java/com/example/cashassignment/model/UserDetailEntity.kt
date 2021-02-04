package com.example.cashassignment.model

import com.google.gson.annotations.SerializedName

data class UserDetailEntity(
    @SerializedName("birthYear")
    val birthYear: Int,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("region")
    val region: String,

    @SerializedName("job")
    val job: String,

    @SerializedName("isMarried")
    val isMarried: Boolean,

    @SerializedName("hasChildren")
    val hasChildren: Boolean
)