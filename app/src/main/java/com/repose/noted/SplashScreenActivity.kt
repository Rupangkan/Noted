package com.repose.noted

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val imageView: ImageView = findViewById(R.id.imageViewNoted)
        val text: TextView = findViewById(R.id.text)
        text.alpha = 0f
        imageView.alpha = 0f
        text.animate().setDuration(800).alpha(1f).withEndAction{
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
        imageView.animate().setDuration(800).alpha(1f).withEndAction{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()

        }

    }
}