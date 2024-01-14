package com.alejandroglzdev.takeyourcreatine.domain

import com.alejandroglzdev.takeyourcreatine.data.UserRegistersRepository
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserRegisters
import javax.inject.Inject

class GetUserRegistersUseCase @Inject constructor(
    private val userRegistersRepository: UserRegistersRepository
){
    suspend operator fun invoke(): List<UserRegisters> {
        return  userRegistersRepository.getUserDataFromDatabase()
    }
}