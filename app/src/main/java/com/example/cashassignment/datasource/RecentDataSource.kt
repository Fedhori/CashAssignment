package com.example.cashassignment.datasource

import android.app.Activity
import android.content.SharedPreferences
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.cashassignment.BaseService
import com.example.cashassignment.api.TaskApi
import com.example.cashassignment.di.KoinApplication
import com.example.cashassignment.enumclasses.AuthType
import com.example.cashassignment.enumclasses.Statuses
import com.example.cashassignment.model.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class RecentDataSource(
) : PageKeyedDataSource<Int, TaskEntity>(), CoroutineScope by MainScope() {

    private val baseService = BaseService.getInstance()

    private val taskApi = baseService.create(TaskApi::class.java)

    private val sharedPreferences: SharedPreferences = KoinApplication.instance.context().
    getSharedPreferences("storage", Activity.MODE_PRIVATE)

    private fun getToken(): String{
        return sharedPreferences.getString("token", "") ?: ""
    }

    private fun getAuthType(): AuthType {
        return AuthType.valueOf(sharedPreferences.getString("authType", "PHONE")?: "")
    }


    private fun getRecentData(
        page: Int,
        onResult: (tasks: List<TaskEntity>?) -> Unit){

        var data: List<TaskEntity>?

        launch(Dispatchers.Main){
            data = taskApi.getRecentPageData(
                token = getToken(),
                authType = getAuthType(),
                page = page).body()?.toList()

            onResult(data)
        }
    }


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TaskEntity>
    ) {
        launch(Dispatchers.Main){
            getRecentData(page = 0){
                callback.onResult(it ?: listOf(), null, 1)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TaskEntity>) {
        launch(Dispatchers.Main){
            getRecentData(page = params.key){
                callback.onResult(it ?: listOf(), params.key + 1)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TaskEntity>) {

    }
}