package com.example.loginandsignupwithfirebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginandsignupwithfirebase.databinding.ActivityAddNoteBinding
import com.example.loginandsignupwithfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.ref.Reference

class AddNote : AppCompatActivity() {
    private val binding: ActivityAddNoteBinding by lazy {
        ActivityAddNoteBinding.inflate(layoutInflater)
    }
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // initialize firebase database reference

        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference
        binding.SaveNotebutton.setOnClickListener {

            // get text form edit text
            val title = binding.editttitle.text.toString()
            val description = binding.edittdescri.text.toString()

            if (title.isEmpty() && description.isEmpty()) {
                Toast.makeText(this, "Please enter title and description", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val currentUser: FirebaseUser? = auth.currentUser
                currentUser?.let { user ->

                    // generate unique key for note
                    val noteKey =
                        databaseReference.child("users").child(user.uid).child("notes").push().key

                    // note item instance

                    val noteItem = NoteItem(title, description,noteKey ?:"")
                    if (noteKey != null) {
                        // add notes to the user note
                        databaseReference.child("users").child(user.uid).child("notes")
                            .child(noteKey).setValue(noteItem)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        this,
                                        "Note added successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    finish()
                                } else {
                                    Toast.makeText(this, "Failed to add note", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }


                    }


                }
            }
        }

    }
}
