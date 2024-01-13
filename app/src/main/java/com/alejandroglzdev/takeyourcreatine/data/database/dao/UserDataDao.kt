package com.alejandroglzdev.takeyourcreatine.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserData

@Dao
interface UserDataDao {
    @Query("SELECT * FROM user_data")
    fun getAllUserData(): List<UserData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(vararg userData: UserData)
    @Update
    fun updateUserData(vararg userData: UserData)
}