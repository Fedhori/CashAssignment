package com.example.cashassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cashassignment.model.BannerEntity
import com.example.cashassignment.model.BundleEntity
import com.example.cashassignment.model.TaskEntity
import com.example.cashassignment.repository.HomeRepository
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class HomeViewModel: ViewModel(), KoinComponent {

    private val homeRepository : HomeRepository by inject()

    fun getBannerData() : LiveData<List<BannerEntity>>{
        return homeRepository.getBannerData()
    }

    fun getTaskData() : LiveData<List<TaskEntity>>{
        return homeRepository.getTaskData()
    }

    fun getBundleData() : LiveData<List<BundleEntity>>{
        return homeRepository.getBundleData()
    }
}
