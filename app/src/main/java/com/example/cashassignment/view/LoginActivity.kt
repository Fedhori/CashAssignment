package com.example.cashassignment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.cashassignment.R
import com.example.cashassignment.viewmodel.LoginViewModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity(), CoroutineScope by MainScope(){

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login_login.setOnClickListener{
            loginWithEmailAndPassword(
                email = "${editText_login_phoneNumber.text}@selectstar.co.kr",
                password = editText_login_password.text.toString()
            )
        }
    }

    private fun loginWithEmailAndPassword(email: String, password: String){

        val tokenLiveData = loginViewModel.loginWithEmailAndPassword(email, password)

        tokenLiveData.observe(this, androidx.lifecycle.Observer {
            when(it){
                "failed" -> Toast.makeText(this, "NOPE", Toast.LENGTH_SHORT).show()

                else -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        })
    }
}