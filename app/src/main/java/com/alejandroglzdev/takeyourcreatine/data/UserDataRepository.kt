package com.alejandroglzdev.takeyourcreatine.data

import com.alejandroglzdev.takeyourcreatine.data.database.dao.UserDataDao
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserData
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val userDataDao: UserDataDao
) {
    suspend fun getUserDataFromDatabase(): UserData? {
        val response = userDataDao.getAllUserData()
        return if (response.isNotEmpty()){
            response.last()
        }else{
            null
        }

    }

    suspend fun deleteUserData() {
        userDataDao.deleteAllUserData()
    }

    suspend fun insertUserData(userData: UserData) {
        userDataDao.insertUserData(userData)
    }
}