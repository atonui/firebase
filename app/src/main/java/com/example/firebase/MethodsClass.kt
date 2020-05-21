package com.example.firebase

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AlertDialog

class MethodsClass(var context: Context) {
	
	//    function to display message to user
	fun showMessage(title: String, message: String) {
		
		val dialogBox = AlertDialog.Builder(context)
		dialogBox.setTitle(title)
		dialogBox.setMessage(message)
		dialogBox.setPositiveButton("Ok") { dialog, which -> dialog.dismiss() }
		dialogBox.create().show()
	}
	
	//    progressbar code
	fun  showProgress(): ProgressDialog {
		val progress = ProgressDialog(context)
		progress.setTitle("Saving...")
		progress.setMessage("Please wait as data is being saved.")
		return progress
	}
}