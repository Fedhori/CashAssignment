package com.example.cashassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cashassignment.model.BannerEntity
import com.example.cashassignment.model.TaskEntity
import com.example.cashassignment.repository.HomeRepository

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val homeRepository = HomeRepository(application)

    fun getBannerData() : LiveData<List<BannerEntity>>{
        return homeRepository.getBannerData()
    }

    fun getNotLoginBannerData() : LiveData<List<BannerEntity>>{
        return homeRepository.getNotLoginBannerData("KO")
    }

    fun getTaskData() : LiveData<List<TaskEntity>>{
        return homeRepository.getTaskData()
    }

    fun getNotLoginTaskData() : LiveData<List<TaskEntity>>{
        return homeRepository.getNotLoginTaskData("KO")
    }
}