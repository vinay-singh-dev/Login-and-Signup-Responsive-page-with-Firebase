package com.example.loginandsignupwithfirebase

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginandsignupwithfirebase.databinding.ActivityAllNotesBinding
import com.example.loginandsignupwithfirebase.databinding.DialougeUpdateNotesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllNotes : AppCompatActivity(),NoteAdapter.OnItemClickListener {
    private val binding: ActivityAllNotesBinding by lazy {
        ActivityAllNotesBinding.inflate(layoutInflater)
    }
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        recyclerView = binding.noterecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        // initialize firebase database reference
        databaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        val currentUser: FirebaseUser? = auth.currentUser
        currentUser?.let { user ->
            val noteReference: DatabaseReference =
                databaseReference.child("users").child(user.uid).child("notes")
            noteReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val noteList: MutableList<NoteItem> = mutableListOf<NoteItem>()
                    for (noteSnapshot: DataSnapshot in snapshot.children) {
                        val note: Any? = noteSnapshot.getValue(NoteItem::class.java)
                        note?.let {
                            noteList.add(it as NoteItem)
                        }
                    }
                    noteList.reverse()
                    val adapter = NoteAdapter(noteList, this@AllNotes)
                    recyclerView.adapter = adapter
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        }
    }

    override fun onDeleteClick(noteId: String) {
        val currentUser: FirebaseUser? = auth.currentUser
        currentUser?.let { user ->
            val noteReference: DatabaseReference =
                databaseReference.child("users").child(user.uid).child("notes")
            noteReference.child(noteId).removeValue()
        }
    }

    override fun onUpdateClick(noteId:String, title:String, description: String) {
        val dialogBinding = DialougeUpdateNotesBinding.inflate(LayoutInflater.from(this))
        val dialog:AlertDialog = AlertDialog.Builder(this).setView(dialogBinding.root)
            .setTitle("update notes")
            .setPositiveButton("update") {dialog, _ ->
                val newTitle = dialogBinding.updatetitle.text.toString()
                val newDescription = dialogBinding.updatedescription.text.toString()
                updateNoteDatabase(noteId,newTitle,newDescription)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel1") {dialog,_ ->
                dialog.dismiss()
            }
            .create()
            dialogBinding.updatetitle.setText(title)
            dialogBinding.updatedescription.setText(description)
            dialog.show()




    fun updateNoteDatabase(noteId: String, newTitle: String, newDescription: String) {
        val currentUser: FirebaseUser? = auth.currentUser
        currentUser?.let { user ->
            val noteReference: DatabaseReference =
                databaseReference.child("users").child(user.uid).child("notes")
            val updateNote = NoteItem(newTitle, newDescription, noteId)
            noteReference.child(noteId).setValue(updateNote)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"Notes updated successfully",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,"Notes update unsuccessful ",Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }
    }

    override fun updateNoteDatabase(noteId: String, newTitle: String, newDescription: String) {

    }


}