package com.example.cashassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cashassignment.model.BannerEntity
import com.example.cashassignment.repository.HomeRepository

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val homeRepository = HomeRepository(application)

    fun getBannerData() : LiveData<List<BannerEntity>>{
        return homeRepository.getBannerData()
    }

    fun getNotLoginBannerData() : LiveData<List<BannerEntity>>{
        return homeRepository.getNotLoginBannerData("KO")
    }
}