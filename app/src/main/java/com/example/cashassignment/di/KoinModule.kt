package com.example.cashassignment.di

import com.example.cashassignment.repository.HomeRepository
import com.example.cashassignment.repository.LoginRepository
import com.example.cashassignment.viewmodel.HomeViewModel
import com.example.cashassignment.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.experimental.builder.factory
import org.koin.experimental.builder.single

val appModule = module{
    single<HomeRepository>()
    single<LoginRepository>()
}

val viewModelModule = module{
    viewModel{ HomeViewModel() }
    viewModel{ LoginViewModel() }
}