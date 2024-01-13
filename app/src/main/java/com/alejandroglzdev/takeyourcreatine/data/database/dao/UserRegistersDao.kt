package com.alejandroglzdev.takeyourcreatine.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserRegisters

@Dao
interface UserRegistersDao {
    @Query("SELECT * FROM user_registers")
    fun getAll(): List<UserRegisters>

    @Update
    fun updateUserData(vararg userRegisters: UserRegisters)
}