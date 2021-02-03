package com.example.cashassignment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class LoginRepository {

    val auth = FirebaseAuth.getInstance()

    fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): LiveData<String?> {

        val tokenLiveData = MutableLiveData<String?>()

        GlobalScope.launch(Dispatchers.Main){
            try {
                auth.signInWithEmailAndPassword(email,password).await()
                Log.d("test", "ok!")
                tokenLiveData.value = FirebaseAuth.getInstance().getAccessToken(false).await().token
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        return tokenLiveData
    }
}