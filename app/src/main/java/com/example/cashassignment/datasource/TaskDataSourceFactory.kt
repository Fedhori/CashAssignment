package com.example.cashassignment.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.cashassignment.model.TaskEntity

class TaskDataSourceFactory : DataSource.Factory<Int, TaskEntity>(){

    private val mutableLiveData: MutableLiveData<TaskDataSource> = MutableLiveData()
    private var taskDataSource: TaskDataSource? = null

    override fun create(): DataSource<Int, TaskEntity> {
        taskDataSource = TaskDataSource()
        mutableLiveData.postValue(taskDataSource)
        return taskDataSource!!
    }

}