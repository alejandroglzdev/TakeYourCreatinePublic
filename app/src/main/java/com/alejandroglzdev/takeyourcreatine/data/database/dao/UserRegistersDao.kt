package com.alejandroglzdev.takeyourcreatine.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserRegisters

@Dao
interface UserRegistersDao {
    @Query("SELECT * FROM user_registers")
    suspend fun getUserRegisters(): List<UserRegisters>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserRegisters(vararg userRegisters: UserRegisters)

    @Update
    suspend fun updateUserRegisters(vararg userRegisters: UserRegisters)

    @Query("DELETE FROM user_registers")
    suspend fun deleteUserRegisters()
}