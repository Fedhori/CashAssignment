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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository(application: Application) {

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

        notLoginBannerApi.getBanners(country).enqueue(object : Callback<List<BannerEntity>> {
            override fun onResponse(call: Call<List<BannerEntity>>, response: Response<List<BannerEntity>>) {

                val bannerEntities = response.body()?.toList()

                if (bannerEntities != null) {
                    for(bannerEntity in bannerEntities){
                        Log.d("test", " \nname: ${bannerEntity.name} \n" +
                                "type: ${bannerEntity.type} \n" +
                                "Url: ${bannerEntity.thumbnailUrl} \n" +
                                "appGuideCoreId: ${bannerEntity.appGuideCoreId} \n" +
                                "taskId: ${bannerEntity.taskId} \n" +
                                "idx: ${bannerEntity.idx} \n\n")
                    }
                }

                liveData.value = bannerEntities
            }

            override fun onFailure(call: Call<List<BannerEntity>>, t: Throwable) {
                t.stackTrace
            }
        })

        return liveData
    }

    fun getTaskData(): LiveData<List<TaskEntity>>{
        val TaskEntities = MutableLiveData<List<TaskEntity>>()

        return TaskEntities
    }

    fun getNotLoginTaskData(country: String, page: Int = 0, category: TaskCategory = TaskCategory.ALL, orderStrategy: TaskOrderStrategy = TaskOrderStrategy.NEW): LiveData<List<TaskEntity>>{

        val TaskEntities = MutableLiveData<List<TaskEntity>>()

        notLoginTaskApi.getTasks(country, page, category, orderStrategy).enqueue(object : Callback<List<TaskEntity>> {
            override fun onResponse(call: Call<List<TaskEntity>>, response: Response<List<TaskEntity>>) {

                val TaskEntities = response.body()?.toList()

                if (TaskEntities != null) {
                    for(TaskEntity in TaskEntities){
                        Log.d("Result", " \nname: ${TaskEntity.title}")
                    }
                }
            }

            override fun onFailure(call: Call<List<TaskEntity>>, t: Throwable) {
                t.stackTrace
            }
        })

        return TaskEntities
    }
}