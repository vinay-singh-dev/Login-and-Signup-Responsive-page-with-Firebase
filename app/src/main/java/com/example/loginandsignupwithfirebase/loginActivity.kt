package com.example.loginandsignupwithfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginandsignupwithfirebase.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class loginActivity : AppCompatActivity() {
    private val binding:ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        // check if user is already logged in
        val currentUser: FirebaseUser? = auth.currentUser

        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(binding.root)

        // Initialize Firebase auth
        auth = FirebaseAuth.getInstance()


        binding.loginButton.setOnClickListener {
            val username = binding.Username.text.toString()
            val password = binding.Password.text.toString()

            if(username.isEmpty() || password.isEmpty() ) {
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(username,password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Sign in successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Sign in failed :${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        binding.SignUpButton.setOnClickListener{
            startActivity(Intent(this,signupActivity::class.java))
            finish()
        }
    }
}