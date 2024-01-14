package com.alejandroglzdev.takeyourcreatine

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

    val userData = MutableLiveData<UserData>()
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
}