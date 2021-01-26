package com.example.cashassignment.model

import com.example.cashassignment.enumclasses.BundleType
import com.google.gson.annotations.SerializedName

data class BundleEntity (
    @SerializedName("title")
    val title: String,

    @SerializedName("bundleType")
    val bundleType: BundleType,

    @SerializedName("taskTitles")
    val taskTitles: List<TaskEntity>
)