package com.alejandroglzdev.takeyourcreatine.data

import com.alejandroglzdev.takeyourcreatine.data.database.dao.UserDataDao
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserData
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val userDataDao: UserDataDao
) {
    suspend fun getUserDataFromDatabase(): UserData {
        val response = userDataDao.getAllUserData()
        return response.first()
    }

    suspend fun insertUserData(userData: UserData) {
        userDataDao.insertUserData(userData)
    }

    suspend fun updateUserData(userData: UserData) {
        userDataDao.updateUserData(userData)
    }
}