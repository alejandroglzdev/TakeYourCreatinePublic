package com.alejandroglzdev.takeyourcreatine.domain

import com.alejandroglzdev.takeyourcreatine.data.UserRegistersRepository
import javax.inject.Inject

class DeleteUserRegistersUseCase @Inject constructor(
    private val userRegistersRepository: UserRegistersRepository
) {
    suspend operator fun invoke() {
        return userRegistersRepository.deleteUserRegisters()
    }
}