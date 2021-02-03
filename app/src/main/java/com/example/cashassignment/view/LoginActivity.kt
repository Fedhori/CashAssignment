package com.example.cashassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cashassignment.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login_login.setOnClickListener{
            val token = login("${editText_login_phoneNumber.text}@selectstar.co.kr", editText_login_password.text.toString())
            Log.d("test", "$token")
        }
    }

    private fun login(email: String, password: String): Task<GetTokenResult>? {

        val auth = FirebaseAuth.getInstance()
        var token: Task<GetTokenResult>? = null

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful) {
                    token = auth.getAccessToken(false)
                } else {
                    Log.w("test", "failure")
                }
            }
        return token
    }
}