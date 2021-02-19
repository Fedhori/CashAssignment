package com.example.cashassignment.datasource

import android.app.Activity
import android.content.SharedPreferences
import androidx.paging.PageKeyedDataSource
import com.example.cashassignment.BaseService
import com.example.cashassignment.api.DibsApi
import com.example.cashassignment.api.TaskApi
import com.example.cashassignment.api.TaskNotLoginApi
import com.example.cashassignment.di.KoinApplication
import com.example.cashassignment.enumclasses.AuthType
import com.example.cashassignment.enumclasses.Statuses
import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.enumclasses.TaskOrderStrategy
import com.example.cashassignment.model.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DibsDataSource(
) : PageKeyedDataSource<Int, TaskEntity>(), CoroutineScope by MainScope() {

    private val baseService = BaseService.getInstance()

    private val dibsApi = baseService.create(DibsApi::class.java)

    private val sharedPreferences: SharedPreferences = KoinApplication.instance.context().
    getSharedPreferences("storage", Activity.MODE_PRIVATE)

    private fun getToken(): String{
        return sharedPreferences.getString("token", "") ?: ""
    }

    private fun getAuthType(): AuthType {
        return AuthType.valueOf(sharedPreferences.getString("authType", "PHONE")?: "")
    }


    private fun getDibsData(
        page: Int,
        statuses: List<Statuses>,
        onResult: (tasks: List<TaskEntity>?) -> Unit){

        var data: List<TaskEntity>?

        launch(Dispatchers.Main){
            data = dibsApi.getDibsPageData(
                token = getToken(),
                authType = getAuthType(),
                page = page,
                statuses = statuses).body()?.toList()

            onResult(data)
        }
    }


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TaskEntity>
    ) {
        launch(Dispatchers.Main){
            getDibsData(page = 0, statuses = listOf(Statuses.IN_PROGRESS, Statuses.STOPPED, Statuses.FINISHED)){
                callback.onResult(it ?: listOf(), null, 1)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TaskEntity>) {
        launch(Dispatchers.Main){
            getDibsData(page = params.key, statuses = listOf(Statuses.IN_PROGRESS, Statuses.STOPPED, Statuses.FINISHED)){
                callback.onResult(it ?: listOf(), params.key + 1)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TaskEntity>) {

    }
}