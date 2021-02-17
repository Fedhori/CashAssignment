package com.example.cashassignment.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.cashassignment.BaseService
import com.example.cashassignment.api.TaskApi
import com.example.cashassignment.api.TaskNotLoginApi
import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.enumclasses.TaskOrderStrategy
import com.example.cashassignment.model.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class TaskDataSource : PageKeyedDataSource<Int, TaskEntity>(), CoroutineScope by MainScope() {

    private val baseService = BaseService.getInstance()
    private val taskApi = baseService.create(TaskNotLoginApi::class.java)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TaskEntity>
    ) {
        launch(Dispatchers.Main){

            val tasks = taskApi.getTasks(
                country = "KO",
                page = 0,
                category = TaskCategory.ALL,
                orderStrategy = TaskOrderStrategy.NEW)
                .body()?.toList()

            callback.onResult(tasks ?: listOf(), null, 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TaskEntity>) {
        launch(Dispatchers.Main){
            val tasks = taskApi.getTasks(
                country = "KO",
                page = params.key,
                category = TaskCategory.ALL,
                orderStrategy = TaskOrderStrategy.NEW)
                .body()?.toList()

            callback.onResult(tasks ?: listOf(), params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TaskEntity>) {
        TODO("Not yet implemented")
    }
}