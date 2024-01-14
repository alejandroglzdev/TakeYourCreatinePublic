package com.alejandroglzdev.takeyourcreatine.domain

import com.alejandroglzdev.takeyourcreatine.data.UserDataRepository
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserData
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    suspend operator fun invoke(): UserData? {
        return userDataRepository.getUserDataFromDatabase()
    }
}