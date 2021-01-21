package com.example.cashassignment.model

import com.example.cashassignment.enumclasses.Level
import com.example.cashassignment.enumclasses.MissionType
import com.example.cashassignment.enumclasses.TaskUserStatus
import com.google.gson.annotations.SerializedName

data class TaskEntity (
    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("showingPrice")
    val showingPrice: String,

    @SerializedName("targetAmount")
    val targetAmount: Int,

    @SerializedName("progress")
    val progress: Int,

    @SerializedName("mainSmallThumbnailUrl")
    val mainSmallThumbnailUrl: String,

    @SerializedName("mainBigThumbnailUrl")
    val mainBigThumbnailUrl: String,

    @SerializedName("level")
    val level: Level,

    @SerializedName("missionType")
    val missionType: MissionType,

    @SerializedName("isNewTutorialOrFaq")
    val isNewTutorialOrFaq: Boolean,

    @SerializedName("minimumVersion")
    val minimumVersion: Int,

    @SerializedName("canTakeScreenshot")
    val canTakeScreenshot: Boolean,

    @SerializedName("isSupportPlatform")
    val isSupportPlatform: Boolean,

    @SerializedName("userCount")
    val userCount: Int,

    @SerializedName("maxCount")
    val maxCount: Int,

    @SerializedName("isDibs")
    val isDibs: Boolean,

    @SerializedName("taskUserStatus")
    val taskUserStatus: TaskUserStatus,

    @SerializedName("taskKey")
    val taskKey: String
)