package com.example.cashassignment.repository

import android.app.Activity
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cashassignment.di.KoinApplication
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class LoginRepository {

    private val sharedPreferences: SharedPreferences = KoinApplication.instance.context().
    getSharedPreferences("storage", Activity.MODE_PRIVATE)
    private val auth = FirebaseAuth.getInstance()

    fun setToken(token: String){
        with(sharedPreferences.edit()){
            putString("token", token)
            apply()
            commit()
        }
    }

    fun setIsLogin(isLogin: Boolean){
        with(sharedPreferences.edit()){
            putBoolean("isLogin", isLogin)
            apply()
            commit()
        }
    }

    fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): LiveData<String?> {

        val tokenLiveData = MutableLiveData<String?>()

        GlobalScope.launch(Dispatchers.Main){
            try {
                auth.signInWithEmailAndPassword(email,password).await()
                tokenLiveData.value = FirebaseAuth.getInstance().getAccessToken(false).await().token

                tokenLiveData.value?.let{
                    setToken(it)
                    setIsLogin(true)
                }
            }catch (e: Exception){
                tokenLiveData.value = "failed"
                e.printStackTrace()
            }
        }
        return tokenLiveData
    }
}