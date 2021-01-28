package com.husam.to_do.db.tables

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*


@Entity
public class List<T> : Serializable{

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "L_ID")
	private val id = null

//    @ColumnInfo(name = "L_TEXT")
//    private val task: String? = null

	@NonNull
	@ColumnInfo(name = "L_DATE")
	private val date: String? = SimpleDateFormat("yyyymmdd").format(Date())

	@ColumnInfo(name = "L_CREATED")
	private val created: Date? = null

	@ColumnInfo(name = "L_CREATED_BY")
	private val created_by: String? = "SYSTEM"

	@ColumnInfo(name = "L_DELETED")
	private val deleted = false

//	fun getid(): Nothing? {
//		return id
//	}
//
//	fun getdate() : Date? {
//		return date
//	}
}