package com.example.cashassignment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.cashassignment.enumclasses.Statuses
import com.example.cashassignment.enumclasses.TaskCategory
import com.example.cashassignment.model.BannerEntity
import com.example.cashassignment.model.BundleEntity
import com.example.cashassignment.model.TaskEntity
import com.example.cashassignment.model.UserDetailEntity
import com.example.cashassignment.repository.HomeRepository
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class HomeViewModel: ViewModel(), KoinComponent {

    private val homeRepository : HomeRepository by inject()

    fun checkIsLogin(): Boolean{
        return homeRepository.checkIsLogin()
    }

    fun getUserDetail(): LiveData<UserDetailEntity>{
        return homeRepository.getUserDetail()
    }

    fun getBannerData() : LiveData<List<BannerEntity>>{
        return homeRepository.getBannerData()
    }

    fun postDibs(taskId: Long){
        homeRepository.postDibs(taskId)
    }

    fun deleteDibs(taskId: Long){
        homeRepository.deleteDibs(taskId)
    }

    fun getRecentPageData() : LiveData<PagedList<TaskEntity>>{
        return homeRepository.getRecentPageData()
    }

    fun getDibsPageData() : LiveData<PagedList<TaskEntity>>{
        return homeRepository.getDibsPageData()
    }

    fun getTaskData(category: TaskCategory = TaskCategory.ALL) : LiveData<List<TaskEntity>>{
        return homeRepository.getTaskData(category)
    }

    fun getTaskPageData(category: TaskCategory = TaskCategory.ALL) : LiveData<PagedList<TaskEntity>>{
        return homeRepository.getTaskPageData(category)
    }

    fun getBundleData() : LiveData<List<BundleEntity>>{
        return homeRepository.getBundleData()
    }
}
