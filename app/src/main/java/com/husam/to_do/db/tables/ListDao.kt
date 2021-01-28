package com.husam.to_do.db.tables

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ListDao{

    @Query("SELECT * FROM LIST ORDER BY L_ID DESC")
    fun getAllLists(): List<List<Any?>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list : List<Any?>)

    @Query("DELETE FROM LIST")
    suspend fun deleteAll()
}