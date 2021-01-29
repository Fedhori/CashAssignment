package com.example.cashassignment.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cashassignment.BaseService
import com.example.cashassignment.api.*
import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.enumclasses.TaskOrderStrategy
import com.example.cashassignment.model.BannerEntity
import com.example.cashassignment.model.BundleEntity
import com.example.cashassignment.model.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class HomeRepository() : CoroutineScope by MainScope(){

    private val baseService = BaseService.getInstance()

    private val bannerApi = baseService.create(BannerApi::class.java)
    private val notLoginBannerApi = baseService.create(BannerNotLoginApi::class.java)

    private val taskApi = baseService.create(TaskApi::class.java)
    private val notLoginTaskApi = baseService.create(TaskNotLoginApi::class.java)

    private val bundleApi = baseService.create(BundleApi::class.java)
    private val notLoginBundleApi = baseService.create(BundleNotLoginApi::class.java)

    fun getBannerData(): LiveData<List<BannerEntity>>{
        return MutableLiveData<List<BannerEntity>>()
    }

    fun getBannerNotLoginData(country: String): LiveData<List<BannerEntity>>{

        val liveData = MutableLiveData<List<BannerEntity>>()
        launch(Dispatchers.Main){
            liveData.value = notLoginBannerApi.getBanners(country).body()?.toList()
        }
        return liveData
    }

    fun getTaskData(): LiveData<List<TaskEntity>>{
        return MutableLiveData<List<TaskEntity>>()
    }

    fun getTaskNotLoginData(country: String, page: Int = 0, category: TaskCategory = TaskCategory.ALL, orderStrategy: TaskOrderStrategy = TaskOrderStrategy.NEW): LiveData<List<TaskEntity>>{

        val liveData = MutableLiveData<List<TaskEntity>>()
        launch(Dispatchers.Main){
            liveData.value = notLoginTaskApi.getTasks(country, page, category, orderStrategy).body()?.toList()
        }
        return liveData
    }

    fun getBundleData(): LiveData<List<BundleEntity>>{
        return MutableLiveData<List<BundleEntity>>()
    }

    fun getBundleNotLoginData(country: String): LiveData<List<BundleEntity>>{

        val liveData = MutableLiveData<List<BundleEntity>>()
        launch(Dispatchers.Main){
            liveData.value = notLoginBundleApi.getBundles(country).body()?.toList()
        }
        return liveData
    }
}