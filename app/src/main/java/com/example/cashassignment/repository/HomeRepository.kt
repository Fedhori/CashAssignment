package com.example.cashassignment.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cashassignment.BaseService
import com.example.cashassignment.api.BannerApi
import com.example.cashassignment.api.NotLoginBannerApi
import com.example.cashassignment.api.NotLoginTaskApi
import com.example.cashassignment.api.TaskApi
import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.enumclasses.TaskOrderStrategy
import com.example.cashassignment.model.BannerEntity
import com.example.cashassignment.model.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class HomeRepository(application: Application) : CoroutineScope by MainScope(){

    private val baseService = BaseService.getInstance()

    private val bannerApi = baseService.create(BannerApi::class.java)
    private val notLoginBannerApi = baseService.create(NotLoginBannerApi::class.java)

    private val taskApi = baseService.create(TaskApi::class.java)
    private val notLoginTaskApi = baseService.create(NotLoginTaskApi::class.java)

    fun getBannerData(): LiveData<List<BannerEntity>>{
        val bannerEntities = MutableLiveData<List<BannerEntity>>()

        return bannerEntities
    }

    fun getNotLoginBannerData(country: String): LiveData<List<BannerEntity>>{

        val liveData = MutableLiveData<List<BannerEntity>>()
        launch(Dispatchers.Main){
            liveData.value = notLoginBannerApi.getBanners(country).body()?.toList()
        }
        return liveData

        /*
        notLoginBannerApi.getBanners(country).enqueue(object : Callback<List<BannerEntity>> {
            override fun onResponse(call: Call<List<BannerEntity>>, response: Response<List<BannerEntity>>) {
                liveData.value = response.body()?.toList()
            }

            override fun onFailure(call: Call<List<BannerEntity>>, t: Throwable) {
                t.stackTrace
            }
        })

        return liveData

         */
    }

    fun getTaskData(): LiveData<List<TaskEntity>>{
        val TaskEntities = MutableLiveData<List<TaskEntity>>()

        return TaskEntities
    }

    fun getNotLoginTaskData(country: String, page: Int = 0, category: TaskCategory = TaskCategory.ALL, orderStrategy: TaskOrderStrategy = TaskOrderStrategy.NEW): LiveData<List<TaskEntity>>{

        val liveData = MutableLiveData<List<TaskEntity>>()
        launch(Dispatchers.Main){
            liveData.value = notLoginTaskApi.getTasks(country, page, category, orderStrategy).body()?.toList()
        }
        return liveData
    }
}