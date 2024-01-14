package com.alejandroglzdev.takeyourcreatine

import android.util.Log
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserData
import com.alejandroglzdev.takeyourcreatine.domain.DeleteUserDataUseCase
import com.alejandroglzdev.takeyourcreatine.domain.GetUserDataUseCase
import com.alejandroglzdev.takeyourcreatine.domain.InsertUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatineViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val insertUserDataUseCase: InsertUserDataUseCase,
    private val deleteUserDataUseCase: DeleteUserDataUseCase
) : ViewModel() {
    fun getUserData() {
        viewModelScope.launch {
            val result = getUserDataUseCase()
            result.let { userData ->
                val userData = userData

            }
        }
    }

    fun insertUserData(userData: UserData) {
        viewModelScope.launch {
            deleteUserDataUseCase()
            insertUserDataUseCase(userData)
        }
    }
}
