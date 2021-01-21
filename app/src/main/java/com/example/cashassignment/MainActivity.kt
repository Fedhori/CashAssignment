package com.example.cashassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.example.cashassignment.R
import com.example.cashassignment.databinding.ActivityMainBinding
import com.example.cashassignment.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val ViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = ViewModel

        binding.textViewHomePoint.setText("Hello?")

        binding.buttonHomeAlarm.setOnClickListener {
            Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show()
            Log.d("Hello", "Hello")
        }

        val bannerData = ViewModel.getNotLoginBannerData()

    }
}