package com.example.loginandsignupwithfirebase

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
//import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.loginandsignupwithfirebase.databinding.ActivityWelcomeScreenBinding

class Welcomescreen : AppCompatActivity() {
    private val binding : ActivityWelcomeScreenBinding by lazy {
        ActivityWelcomeScreenBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,loginActivity::class.java))
            finish()
        },3000)
        val welcomeText = "Welcome"
        val spannableString = SpannableString(welcomeText)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#4980FF")),0,5,0)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#4980FF")),5,welcomeText.length,0)

        binding.welcomeText.text = spannableString



    }
}