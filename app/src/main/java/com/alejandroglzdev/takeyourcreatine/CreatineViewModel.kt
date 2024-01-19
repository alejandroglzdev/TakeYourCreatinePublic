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
import com.alejandroglzdev.takeyourcreatine.domain.UpdateUserDataUseCase
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
    private val updateUserDataUseCase: UpdateUserDataUseCase,
    private val utils: Utils
) : ViewModel() {

    //TODO: Implementar dar review en Google Play
    //TODO: Implementar carga de creatina
    //TODO: Implementar TimePicker
    //TODO: Implementar notificaciones

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

    fun insertCreatineIntake(userDataBD: UserData) {
        if (userData.value == null) {
            viewModelScope.launch {
                deleteUserDataUseCase()

            }
            viewModelScope.launch {
                insertUserDataUseCase(userDataBD)

            }
        } else {
            viewModelScope.launch {
                val intake = userDataBD.creatineIntake
                val newIntake = if (intake != null) {
                    (intake * 0.1).toInt()
                } else {
                    0
                }

                val newUserData = UserData(
                    id = userData.value!!.id,
                    creatineIntake = newIntake,
                    onboard = false,
                    notifications = userData.value!!.notifications,
                    hour = userData.value!!.hour
                )

                updateUserData(newUserData)

            }
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

    private fun updateUserData(userData: UserData) {
        viewModelScope.launch {
            updateUserDataUseCase(userData)
        }
    }

    fun updateUserDataAndReloadData(userData: UserData) {
        updateUserData(userData)
        getUserData()
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
