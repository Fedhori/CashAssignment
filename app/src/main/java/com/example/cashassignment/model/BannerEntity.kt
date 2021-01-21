package com.example.cashassignment.model

import com.google.gson.annotations.SerializedName

data class BannerEntity (
    @SerializedName("type")
    val type: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,

    @SerializedName("appGuideCoreId")
    val appGuideCoreId: Long,

    @SerializedName("noticeTitleId")
    val noticeTitleId: Long,

    @SerializedName("taskId")
    val taskId: Long,

    @SerializedName("idx")
    val idx: Long
)