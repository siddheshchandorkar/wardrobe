package com.siddhesh.wardrobe.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.siddhesh.wardrobe.R


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions.makeSceneTransitionAnimation(this)
                startActivity(
                    Intent(this, MainActivity::class.java),
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                );
                finish()
            } else {
                startActivity(Intent(this, MainActivity::class.java));
                finish()
            }


        }, 3000)
    }
}