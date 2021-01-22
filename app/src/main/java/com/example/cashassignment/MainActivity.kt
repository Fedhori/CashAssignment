package com.example.cashassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.example.cashassignment.databinding.ActivityMainBinding
import com.example.cashassignment.view.BannerViewPagerAdapter
import com.example.cashassignment.viewmodel.HomeViewModel
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    private lateinit var bannerViewPagerAdapter: BannerViewPagerAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        bannerViewPagerAdapter = BannerViewPagerAdapter(this)
        binding.viewPager2HomeBanner.adapter = bannerViewPagerAdapter

        val bannerData = viewModel.getNotLoginBannerData()
        val taskData = viewModel.getNotLoginTaskData()

        bannerData.observe(this, androidx.lifecycle.Observer { bannerList ->
            bannerViewPagerAdapter.submitList(bannerList)
        })
    }
}