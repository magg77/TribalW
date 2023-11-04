package com.example.tribalw.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.tribalw.MainActivity
import com.example.tribalw.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen: SplashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashScreen.setKeepOnScreenCondition{true}
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
            runBlocking {
                delay(1000)
            }
        }

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}