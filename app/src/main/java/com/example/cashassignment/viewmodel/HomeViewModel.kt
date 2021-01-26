package com.example.cashassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cashassignment.model.BannerEntity
import com.example.cashassignment.model.BundleEntity
import com.example.cashassignment.model.TaskEntity
import com.example.cashassignment.repository.HomeRepository

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val homeRepository = HomeRepository(application)

    fun getBannerData() : LiveData<List<BannerEntity>>{
        return homeRepository.getBannerData()
    }

    fun getBannerNotLoginData() : LiveData<List<BannerEntity>>{
        return homeRepository.getBannerNotLoginData("KO")
    }

    fun getTaskData() : LiveData<List<TaskEntity>>{
        return homeRepository.getTaskData()
    }

    fun getTaskNotLoginData() : LiveData<List<TaskEntity>>{
        return homeRepository.getTaskNotLoginData("KO")
    }

    fun getBundleData() : LiveData<List<BundleEntity>>{
        return homeRepository.getBundleData()
    }

    fun getBundleNotLoginData() : LiveData<List<BundleEntity>>{
        return homeRepository.getBundleNotLoginData("KO")
    }
}