package com.example.firebase

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
//        show all users activity
        imgUsers.setOnClickListener {
            startActivity(Intent(this, UsersActivity::class.java))
        }
        
//        show password functionality
        imgShow.setOnClickListener {
            showPassword()
        }
//        add data to database
        btnCreate.setOnClickListener {
            addUser()
        }
    }
    
    val methodsClass = MethodsClass(this) //instance of class with common functions
    
    fun addUser() {
//        get data from the text files
        val firstName = etFirstName.text.trim().toString()
        val lastName = etLastName.text.trim().toString()
        val email = etEmail.text.trim().toString()
        val password = etPassword.text.toString()
        val id = System.currentTimeMillis()
        
//        initialize progress bar
        val appProgress = methodsClass.showProgress()
        
//        validate user input
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            methodsClass.showMessage("Invalid Data", "Some input fields are missing data")
        } else {
            val my_ref = FirebaseDatabase.getInstance().reference.child("users/$id")
            val user_object = UserSchema(firstName, lastName, email, password)
//            show progress bar as data is being added
            appProgress.show()
            my_ref.setValue(user_object).addOnCompleteListener { task ->
                appProgress.dismiss()
                if (task.isSuccessful) {
                    methodsClass.showMessage("Registration completed.", "Congratulations $firstName! You have been registered!")
                    clearTextFields()
                } else {
                    methodsClass.showMessage("Registration failed.","Oops! Something went wrong with your registration. Please try again.")
                }
            }
        }
    }

    
    fun clearTextFields() {
        etFirstName.text = null
        etLastName.text = null
        etEmail.text = null
        etPassword.text = null
    }
    
    fun showPassword() {
        if (imgShow.tag.toString().equals("Show")) {
            etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            imgShow.tag = "Hide"
        } else {
            etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            imgShow.tag = "Show"
        }
    }

}
