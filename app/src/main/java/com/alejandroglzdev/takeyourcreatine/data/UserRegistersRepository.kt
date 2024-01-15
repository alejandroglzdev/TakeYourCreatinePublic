package com.alejandroglzdev.takeyourcreatine.data

import com.alejandroglzdev.takeyourcreatine.data.database.dao.UserRegistersDao
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserRegisters
import javax.inject.Inject

class UserRegistersRepository @Inject constructor(
    private val userRegistersDao: UserRegistersDao
) {
    suspend fun getUserRegistersFromDatabase(): List<UserRegisters> {
        val response = userRegistersDao.getUserRegisters()
        return if (response.isNotEmpty()) {
            return response
        } else {
            return emptyList()
        }

    }

    suspend fun insertUserRegisters(userRegisters: UserRegisters) {
        userRegistersDao.insertUserRegisters(userRegisters)

    }

    suspend fun deleteUserRegisters() {
        userRegistersDao.deleteUserRegisters()

    }

}