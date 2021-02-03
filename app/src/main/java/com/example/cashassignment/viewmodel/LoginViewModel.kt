package com.example.cashassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cashassignment.repository.HomeRepository
import com.example.cashassignment.repository.LoginRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

@KoinApiExtension
class LoginViewModel : ViewModel(), KoinComponent {
    private val homeRepository : LoginRepository by inject()

    fun loginWithEmailAndPassword(email: String, password: String): LiveData<String?> {
        return homeRepository.loginWithEmailAndPassword(email, password)
    }
}