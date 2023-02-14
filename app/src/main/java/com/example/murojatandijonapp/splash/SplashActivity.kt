package com.example.murojatandijonapp.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.auth.AuthActivity
import com.example.murojatandijonapp.databinding.ActivitySplashBinding
import com.example.murojatandijonapp.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            if (viewModel.isUserNotNull()) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashActivity, AuthActivity::class.java))
                finish()
            }
        }
    }
}