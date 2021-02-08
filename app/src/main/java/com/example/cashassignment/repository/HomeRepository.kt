package com.example.cashassignment.repository

import android.app.Activity
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cashassignment.BaseService
import com.example.cashassignment.api.*
import com.example.cashassignment.di.KoinApplication
import com.example.cashassignment.enumclasses.AuthType
import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.enumclasses.TaskOrderStrategy
import com.example.cashassignment.model.BannerEntity
import com.example.cashassignment.model.BundleEntity
import com.example.cashassignment.model.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class HomeRepository : CoroutineScope by MainScope(){

    private val baseService = BaseService.getInstance()

    private val bannerApi = baseService.create(BannerApi::class.java)
    private val bannerNotLoginApi = baseService.create(BannerNotLoginApi::class.java)

    private val taskApi = baseService.create(TaskApi::class.java)
    private val taskNotLoginApi = baseService.create(TaskNotLoginApi::class.java)

    private val bundleApi = baseService.create(BundleApi::class.java)
    private val bundleNotLoginApi = baseService.create(BundleNotLoginApi::class.java)

    private val privacyDetailApi = baseService.create(PrivacyDetailApi::class.java)

    private val sharedPreferences: SharedPreferences = KoinApplication.instance.context().
    getSharedPreferences("storage", Activity.MODE_PRIVATE)

    private fun checkIsLogin(): Boolean{
        return sharedPreferences.getBoolean("isLogin", false)
    }

    private fun getToken(): String{
        return sharedPreferences.getString("token", "") ?: ""
    }

    private fun getAuthType(): AuthType{
        return AuthType.valueOf(sharedPreferences.getString("authType", "PHONE")?: "")
    }

    fun getBannerData(): LiveData<List<BannerEntity>>{
        val liveData = MutableLiveData<List<BannerEntity>>()

        launch(Dispatchers.Main){
            if(checkIsLogin()){
                liveData.value = bannerApi.getBanners(getToken(), getAuthType()).body()?.toList()
            }
            else{
                liveData.value = bannerNotLoginApi.getBanners("KO").body()?.toList()
            }
        }

        return liveData
    }
    /*
    private fun getBannerLoginData(): LiveData<List<BannerEntity>>{
        //TODO
        return MutableLiveData<List<BannerEntity>>()
    }

   private fun getBannerNotLoginData(country: String): LiveData<List<BannerEntity>>{

        val liveData = MutableLiveData<List<BannerEntity>>()
        launch(Dispatchers.Main){
            liveData.value = notLoginBannerApi.getBanners(country).body()?.toList()
        }
        return liveData
    }
     */

    //TODO need to implement APIs to get parameters
    fun getTaskData(): LiveData<List<TaskEntity>>{

        val liveData = MutableLiveData<List<TaskEntity>>()

        launch(Dispatchers.Main){
            if(checkIsLogin()){
                liveData.value = taskApi.getTasks(
                    token = getToken(),
                    authType = getAuthType(),
                    page = 0,
                    category = TaskCategory.ALL,
                    taskOrderStrategy = TaskOrderStrategy.NEW).body()?.toList()
            }
            else{
                liveData.value = taskNotLoginApi.getTasks(
                    country = "KO",
                    page = 0,
                    category = TaskCategory.ALL,
                    orderStrategy = TaskOrderStrategy.NEW)
                    .body()?.toList()
            }
        }

        return liveData
    }

    /*
    fun getTaskLoginData(): LiveData<List<TaskEntity>>{
        //TODO
        return MutableLiveData<List<TaskEntity>>()
    }

    fun getTaskNotLoginData(country: String, page: Int = 0, category: TaskCategory = TaskCategory.ALL, orderStrategy: TaskOrderStrategy = TaskOrderStrategy.NEW): LiveData<List<TaskEntity>>{

        val liveData = MutableLiveData<List<TaskEntity>>()
        launch(Dispatchers.Main){
            liveData.value = notLoginTaskApi.getTasks(country, page, category, orderStrategy).body()?.toList()
        }
        return liveData
    }
     */

    fun getBundleData(): LiveData<List<BundleEntity>>{

        val liveData = MutableLiveData<List<BundleEntity>>()

        launch(Dispatchers.Main){
            if(checkIsLogin()){
                liveData.value = bundleApi.getBundles(getToken(), getAuthType()).body()?.toList()
            }
            else{
                liveData.value = bundleNotLoginApi.getBundles("KO").body()?.toList()
            }
        }

        return liveData
    }

    /*

    fun getBundleLoginData(): LiveData<List<BundleEntity>>{
        //TODO
        return MutableLiveData<List<BundleEntity>>()
    }

    fun getBundleNotLoginData(country: String): LiveData<List<BundleEntity>>{

        val liveData = MutableLiveData<List<BundleEntity>>()
        launch(Dispatchers.Main){
            liveData.value = notLoginBundleApi.getBundles(country).body()?.toList()
        }
        return liveData
    }

     */
}