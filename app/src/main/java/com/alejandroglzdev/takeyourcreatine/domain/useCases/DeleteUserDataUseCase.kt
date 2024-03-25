package com.alejandroglzdev.takeyourcreatine.domain.useCases

import com.alejandroglzdev.takeyourcreatine.data.UserDataRepository
import javax.inject.Inject

class DeleteUserDataUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository) {
    suspend operator fun invoke() {
        userDataRepository.deleteUserData()
    }
}