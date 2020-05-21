package com.example.firebase

class UserSchema {
	var first_name: String = ""
	var last_Name: String = ""
	var user_email: String = ""
	var user_password: String = ""
	
	constructor(
		firstname: String,
		lastname:String,
		email: String,
		password: String
	) {
		this.first_name = firstname
		this.last_Name = lastname
		this.user_email = email
		this.user_password = password
	}
	
	constructor() {}
	
}