package com.example.cashassignment.di

import com.example.cashassignment.repository.HomeRepository
import com.example.cashassignment.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.experimental.builder.factory
import org.koin.experimental.builder.single

val appModule = module{
    single<HomeRepository>()
}

val viewModelModule = module{
    viewModel{ HomeViewModel() }
}