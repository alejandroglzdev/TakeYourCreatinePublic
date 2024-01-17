package com.alejandroglzdev.takeyourcreatine

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandroglzdev.takeyourcreatine.core.Utils
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserData
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserRegisters
import com.alejandroglzdev.takeyourcreatine.domain.DeleteUserDataUseCase
import com.alejandroglzdev.takeyourcreatine.domain.DeleteUserRegistersUseCase
import com.alejandroglzdev.takeyourcreatine.domain.GetUserDataUseCase
import com.alejandroglzdev.takeyourcreatine.domain.GetUserRegistersUseCase
import com.alejandroglzdev.takeyourcreatine.domain.InsertUserDataUseCase
import com.alejandroglzdev.takeyourcreatine.domain.InsertUserRegistersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CreatineViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val insertUserDataUseCase: InsertUserDataUseCase,
    private val deleteUserDataUseCase: DeleteUserDataUseCase,
    private val getUserRegistersUseCase: GetUserRegistersUseCase,
    private val insertUserRegistersUseCase: InsertUserRegistersUseCase,
    private val deleteUserRegistersUseCase: DeleteUserRegistersUseCase,
    private val utils: Utils
) : ViewModel() {

    val userData = MutableLiveData<UserData>()
    val userRegisters = MutableLiveData<List<UserRegisters>>()
    fun getUserData() {
        viewModelScope.launch {
            val result = getUserDataUseCase()
            result.let {
                userData.postValue(it)

            }
        }
    }

    fun insertUserData(userData: UserData) {
        viewModelScope.launch {
            deleteUserDataUseCase()

        }
        viewModelScope.launch {
            insertUserDataUseCase(userData)

        }
    }

    fun getUserRegisters() {
        viewModelScope.launch {
            val result = getUserRegistersUseCase()
            result.let {
                userRegisters.postValue(it)
            }
        }
    }

    fun insertUserRegisters(userRegisters: UserRegisters) {
        viewModelScope.launch {
            insertUserRegistersUseCase(userRegisters)
        }
    }

    fun deleteUserRegisters() {
        viewModelScope.launch {
            deleteUserRegistersUseCase()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun localDateTimeIsToday(localDateTime: LocalDateTime?): Boolean {
        return utils.localDateTimeIsToday(localDateTime)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun consecutiveDays(registers: List<UserRegisters>): Int {
        return utils.getConsecutiveDaysCount(registers)

    }

    fun returnLocalDateList(registers: List<UserRegisters>): List<LocalDateTime> {
        return utils.returnLocalDateList(registers)
    }
}
