package com.alejandroglzdev.takeyourcreatine.domain

import com.alejandroglzdev.takeyourcreatine.data.UserRegistersRepository
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserRegisters
import javax.inject.Inject

class InsertUserRegistersUseCase @Inject constructor(
    private val userRegistersRepository: UserRegistersRepository
) {
    suspend operator fun invoke(userRegisters: UserRegisters) {
        userRegistersRepository.insertUserRegisters(userRegisters)
    }
}