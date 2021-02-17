package com.example.cashassignment.datasource

import android.app.Activity
import android.content.SharedPreferences
import androidx.paging.PageKeyedDataSource
import com.example.cashassignment.BaseService
import com.example.cashassignment.api.TaskApi
import com.example.cashassignment.api.TaskNotLoginApi
import com.example.cashassignment.di.KoinApplication
import com.example.cashassignment.enumclasses.AuthType
import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.enumclasses.TaskOrderStrategy
import com.example.cashassignment.model.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class TaskDataSource(
    private val category: TaskCategory
) : PageKeyedDataSource<Int, TaskEntity>(), CoroutineScope by MainScope() {

    private val baseService = BaseService.getInstance()

    private val taskApi = baseService.create(TaskApi::class.java)
    private val taskNotLoginApi = baseService.create(TaskNotLoginApi::class.java)

    private val sharedPreferences: SharedPreferences = KoinApplication.instance.context().
    getSharedPreferences("storage", Activity.MODE_PRIVATE)

    private fun checkIsLogin(): Boolean{
        return sharedPreferences.getBoolean("isLogin", false)
    }

    private fun getToken(): String{
        return sharedPreferences.getString("token", "") ?: ""
    }

    private fun getAuthType(): AuthType {
        return AuthType.valueOf(sharedPreferences.getString("authType", "PHONE")?: "")
    }

    private fun getTaskData(
        page: Int,
        category: TaskCategory = TaskCategory.ALL,
        onResult: (tasks: List<TaskEntity>?) -> Unit){

        var data : List<TaskEntity>? = null

        launch(Dispatchers.Main){
            if(checkIsLogin()){
                data = taskApi.getTasks(
                    token = getToken(),
                    authType = getAuthType(),
                    page = page,
                    category = category,
                    taskOrderStrategy = TaskOrderStrategy.NEW).body()?.toList()
            }
            else{
                data = taskNotLoginApi.getTasks(
                    country = "KO",
                    page = page,
                    category = category,
                    orderStrategy = TaskOrderStrategy.NEW)
                    .body()?.toList()
            }

            onResult(data)
        }
    }


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TaskEntity>
    ) {
        launch(Dispatchers.Main){
            getTaskData(page = 0, category = category){
                callback.onResult(it ?: listOf(), null, 1)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TaskEntity>) {
        launch(Dispatchers.Main){
            getTaskData(page = params.key, category = category){
                callback.onResult(it ?: listOf(), params.key + 1)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TaskEntity>) {

    }
}