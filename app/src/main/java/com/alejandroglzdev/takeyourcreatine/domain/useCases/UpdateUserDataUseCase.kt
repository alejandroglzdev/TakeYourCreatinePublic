package com.alejandroglzdev.takeyourcreatine.domain.useCases

import com.alejandroglzdev.takeyourcreatine.data.UserDataRepository
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserData
import javax.inject.Inject

class UpdateUserDataUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    suspend operator fun invoke(vararg userData: UserData) {
        userDataRepository.updateUserData(*userData)
    }
}