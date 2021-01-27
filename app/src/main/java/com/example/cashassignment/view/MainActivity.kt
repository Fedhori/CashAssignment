package com.example.cashassignment.view

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cashassignment.R
import com.example.cashassignment.databinding.ActivityMainBinding
import com.example.cashassignment.model.BundleEntity
import com.example.cashassignment.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.view_mission.view.*
import kotlinx.android.synthetic.main.view_new_mission_task.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var bannerViewPagerAdapter: BannerViewPagerAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        makeStatusBarTransparent()
        initBinding()
        initBanner()
        initMissions()
    }

    private fun makeStatusBarTransparent(){
        // make status bar completely transparent
        val window = window
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initBanner(){
        bannerViewPagerAdapter = BannerViewPagerAdapter(this)
        binding.viewPager2HomeBanner.adapter = bannerViewPagerAdapter

        val bannerData = viewModel.getBannerNotLoginData()

        bannerData.observe(this, androidx.lifecycle.Observer { bannerData ->
            bannerViewPagerAdapter.submitList(bannerData)
        })
    }

    private fun initMissions(){
        initNewMission()
        initMyMission()
        initBundle()
    }

    private fun initNewMission(){
        val newMissionView = MissionView(this)
        val taskViewAdapter = TaskViewAdapter(this)
        val taskData = viewModel.getTaskNotLoginData()

        binding.linearLayoutHomeBundleContainer.addView(newMissionView)
        newMissionView.textView_mission.text = "새로운 미션"
        newMissionView.recyclerView_mission.adapter = taskViewAdapter

        taskData.observe(this, androidx.lifecycle.Observer { taskData ->
            taskViewAdapter.submitList(taskData)
        })
    }

    private fun initMyMission(){
        //TODO implement myMission task
    }

    private fun initBundle(){
        val bundleLiveData = viewModel.getBundleNotLoginData()

        bundleLiveData.observe(this, androidx.lifecycle.Observer { bundleList ->
            setBundle(bundleList)
        })
    }

    private fun setBundle(bundleList: List<BundleEntity>){
        for(bundle in bundleList){
            val missionView = MissionView(this)
            val taskViewAdapter = TaskViewAdapter(this)

            binding.linearLayoutHomeBundleContainer.addView(missionView)
            missionView.textView_mission.text = bundle.title
            missionView.recyclerView_mission.adapter = taskViewAdapter
            taskViewAdapter.submitList(bundle.taskTitles)
        }
    }
}