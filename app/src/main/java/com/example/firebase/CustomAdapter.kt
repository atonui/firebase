package com.example.firebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView

class CustomAdapter(var context: Context, var data: ArrayList<UserSchema>): BaseAdapter() {
	
	private class ViewHolder(row: View?) {
		var firstName: TextView = row?.findViewById(R.id.tvfirstname) as TextView
		var lastName: TextView = row?.findViewById(R.id.tvlastname) as TextView
		var email: TextView = row?.findViewById(R.id.tvemail) as TextView
		var myLayout: LinearLayout = row?.findViewById(R.id.myLayout) as LinearLayout
		
		
	}
	
	override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
		val view:View
		val viewHolder: ViewHolder
		if (convertView == null) {
			val layout = LayoutInflater.from(context)
			view = layout.inflate(R.layout.card_layout, parent,false)
			viewHolder = ViewHolder(view)
			view.tag = viewHolder
		} else {
			view = convertView
			viewHolder = view.tag as ViewHolder
		}
		
		val userSchema: UserSchema = getItem(position) as UserSchema
		viewHolder.firstName.text = userSchema.first_name
		viewHolder.lastName.text = userSchema.last_Name
		viewHolder.email.text = userSchema.user_email
		
		return view as View
	}
	
	override fun getItem(position: Int): Any {
		return data.get(position)
	}
	
	override fun getItemId(position: Int): Long {
		return position.toLong()
	}
	
	override fun getCount(): Int {
		return data.count()
	}
}