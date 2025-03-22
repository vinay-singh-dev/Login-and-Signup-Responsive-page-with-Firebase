package com.example.loginandsignupwithfirebase

import android.content.Intent
import android.os.Bundle
//import android.renderscript.ScriptGroup.Binding
import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginandsignupwithfirebase.databinding.ActivitySignupBinding
import com.example.loginandsignupwithfirebase.databinding.ActivityWelcomeScreenBinding
import com.google.firebase.auth.FirebaseAuth

class signupActivity : AppCompatActivity() {
    private val binding:ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(binding.root)
        // intialization of firebase authentication
        auth = FirebaseAuth.getInstance()



        binding.signInButton.setOnClickListener{
            startActivity(Intent(this,loginActivity::class.java))
            finish()
        }

        binding.RegisterButton.setOnClickListener {
//            get text from edit text field
            val email  = binding.Email.text.toString()
            val username  = binding.Username.text.toString()
            val password = binding.Password.text.toString()
            val repeatPassword = binding.RepeatPassword.text.toString()


            // check if any field is blank
            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {

                Toast.makeText(this, "please fill all the details", Toast.LENGTH_SHORT).show()
            } else if(password != repeatPassword) {
                Toast.makeText(this, "password must be same", Toast.LENGTH_SHORT).show()
            }
             else {
                 auth.createUserWithEmailAndPassword(email,password)
                     .addOnCompleteListener(this) { task ->
                         if (task.isSuccessful) {
                             Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                             startActivity(Intent(this,loginActivity::class.java))
                             finish()
                         }
                         else {
                             Toast.makeText(this, "Registration failed : ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                         }
                         }
                     }

             }
            }

        }


