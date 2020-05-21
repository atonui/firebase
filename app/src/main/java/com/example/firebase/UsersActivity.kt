package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_users)
		getUsers()
	
	}
	val methodsClass = MethodsClass(this)
	
	fun getUsers() {
		val usersArray: ArrayList<UserSchema> = ArrayList()
		val myAdapter = CustomAdapter(this, usersArray)
		val progress = methodsClass.showProgress()
		
//		show progress as we are fetching data from database
		progress.show()
		
		val fbDbRef = FirebaseDatabase.getInstance().reference.child("users")
		
		fbDbRef.addValueEventListener(object: ValueEventListener{
			override fun onDataChange(p0: DataSnapshot) {
//				clear previously added data in the users array
				usersArray.clear()
				for (snap in p0.children) {
					val user = snap.getValue(UserSchema::class.java)
					usersArray.add(user!!)
				}
				myAdapter.notifyDataSetChanged()
				progress.dismiss()
			}
			
			override fun onCancelled(p0: DatabaseError) {
				progress.dismiss()
				methodsClass.showMessage("Access Denied", "You do not have sufficient rights to access this data.")
			}
		})
		user_list.adapter = myAdapter
		
	}
}
