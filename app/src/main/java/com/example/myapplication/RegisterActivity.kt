package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRegister.setOnClickListener {
          performRegister()
        }

        textAlreadyhaveAccount.setOnClickListener {
            Log.d("RegisterActivity", "Try to show login activity")

            //launch loginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnImageUpload.setOnClickListener {
            Log.d("RegisterActivity", "Show Photo Selector")


            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

    }
     var selectedPhotoUri: Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
            //proceed and check what the selected image was
            Log.d("RegisterActivity", "Photo Selected")

            //Shows selected Image
            selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            val bitmapDrawable = BitmapDrawable(bitmap)
            btnImageUpload.setBackgroundDrawable(bitmapDrawable)
        }
    }

    private fun performRegister(){
        val email = textEmail.text.toString()
        val password = textPassword.text.toString()

        //Check if null or empty string
        if (email.isEmpty() || password.isEmpty() ){
            Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT ).show()
            return
        }

        Log.d("RegisterActivity", "Email is: " + email)
        Log.d("RegisterActivity", "Password: $password")

        //Firebase Authentication to create user with email & password
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful)return@addOnCompleteListener

                //else if successful
                Log.d("Main", "Succesfully created user with uid: ${it.result}")

                uploadImagetoFireBaseStorage()
            }
            .addOnFailureListener {
                Log.d("Main", "Failed to create user: ${it.message}" )
                Toast.makeText(this, "Failed to create user", Toast.LENGTH_SHORT ).show()
            }
    }

    private fun uploadImagetoFireBaseStorage() {

        if (selectedPhotoUri == null) return

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("Register", "Succesfuclly uploaded image: ${it.metadata?.path}")


        ref.downloadUrl.addOnSuccessListener {
            Log.d("RegisterActivity", "File Location : $it")

            saveUserToFirebaseDatabase(it.toString())
        }
    }

    }

    private fun saveUserToFirebaseDatabase(profileImageUrl: String){
        val uid = FirebaseAuth.getInstance().uid?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid, textUsername.text.toString(), profileImageUrl   )

        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("RegisterActivity", "Save user to Firebase Database")

                val intent = Intent(this, ShopActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .addOnFailureListener {
                Log.d("RegisterActivity", "Failed to set value to database: $(it.message)")
            }
    }
}

class User(val uid: String, val username: String, val profileImageUrl: String)