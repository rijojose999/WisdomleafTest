package com.example.wisdomleaftest.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.wisdomleaftest.MainActivity
import com.example.wisdomleaftest.R
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {

    private val scheduledExecutor = Executors.newSingleThreadScheduledExecutor()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // Navigation to list page after 5 seconds
        scheduledExecutor.schedule({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 5000, TimeUnit.MILLISECONDS)
    }
}