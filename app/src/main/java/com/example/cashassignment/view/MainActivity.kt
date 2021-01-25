package com.example.cashassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.cashassignment.R
import com.example.cashassignment.databinding.ActivityMainBinding
import com.example.cashassignment.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var bannerViewPagerAdapter: BannerViewPagerAdapter
    private lateinit var taskViewAdapter: TaskViewAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        initBinding()
        initBanner()
        initNewMissionTask()
    }

    private fun initBinding(){
        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_main
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initBanner(){
        bannerViewPagerAdapter = BannerViewPagerAdapter(this)
        binding.viewPager2HomeBanner.adapter = bannerViewPagerAdapter

        val bannerData = viewModel.getNotLoginBannerData()

        bannerData.observe(this, androidx.lifecycle.Observer { bannerData ->
            bannerViewPagerAdapter.submitList(bannerData)
        })
    }

    private fun initNewMissionTask(){
        taskViewAdapter = TaskViewAdapter(this)
        binding.recyclerViewNewMissionTasks.adapter = taskViewAdapter

        val taskData = viewModel.getNotLoginTaskData()

        taskData.observe(this, androidx.lifecycle.Observer { taskData ->
            taskViewAdapter.submitList(taskData)
        })
    }
}