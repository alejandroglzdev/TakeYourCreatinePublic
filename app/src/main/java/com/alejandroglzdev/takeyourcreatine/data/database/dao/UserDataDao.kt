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
    suspend fun getAllUserData(): List<UserData>

    @Query("DELETE FROM user_data")
    suspend fun deleteAllUserData()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData(vararg userData: UserData)
    @Update
    suspend fun updateUserData(vararg userData: UserData)
}