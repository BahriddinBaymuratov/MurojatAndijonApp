package com.example.murojatandijonapp.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.murojatandijonapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportActionBar?.hide()
    }
}