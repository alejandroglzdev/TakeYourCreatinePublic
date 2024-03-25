package com.alejandroglzdev.takeyourcreatine.domain.useCases

import com.alejandroglzdev.takeyourcreatine.data.UserDataRepository
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserData
import javax.inject.Inject

class InsertUserDataUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    suspend operator fun invoke(userData: UserData) {

        val intake = userData.creatineIntake ?: 0
        val finalIntake = (intake * 0.1).toInt()
        val newUserData = UserData(creatineIntake = finalIntake)

        userDataRepository.insertUserData(newUserData)
    }
}