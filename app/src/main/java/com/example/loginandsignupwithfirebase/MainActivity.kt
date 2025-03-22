package com.example.loginandsignupwithfirebase

import android.content.Intent
import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginandsignupwithfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.Createnotesbutton.setOnClickListener{
        startActivity(Intent(this,AddNote::class.java))
        }
        binding.Opennotesbutton.setOnClickListener{
        startActivity(Intent(this,AllNotes::class.java))
        }

    }
}