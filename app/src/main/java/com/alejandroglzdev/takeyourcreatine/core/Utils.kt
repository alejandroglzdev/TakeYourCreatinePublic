package com.alejandroglzdev.takeyourcreatine.core

import android.os.Build
import androidx.annotation.RequiresApi
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserRegisters
import java.time.LocalDateTime
import javax.inject.Inject

class Utils @Inject constructor() {
    @RequiresApi(Build.VERSION_CODES.O)
    fun localDateTimeIsToday(localDateTime: LocalDateTime?): Boolean {
        if (localDateTime != null) {
            return ((localDateTime.dayOfMonth == LocalDateTime.now().dayOfMonth) &&
                    (localDateTime.monthValue == LocalDateTime.now().monthValue) &&
                    (localDateTime.year == LocalDateTime.now().year))
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getConsecutiveDaysCount(registers: List<UserRegisters>): Int {
        var consecutiveDaysCount = 1
        val localDateList = returnLocalDateList(registers)


        for (i in 1 until localDateList.size) {
            val currentDate = registers[i].register
            val previousDate = registers[i - 1].register

            if (isConsecutiveDay(currentDate, previousDate)) {
                consecutiveDaysCount++
            } else {
                break // No longer consecutive, exit the loop
            }
        }

        return consecutiveDaysCount
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isConsecutiveDay(currentDate: LocalDateTime, previousDate: LocalDateTime): Boolean {
        return currentDate.toLocalDate().minusDays(1) == previousDate.toLocalDate()
    }

    fun returnLocalDateList(registers: List<UserRegisters>): List<LocalDateTime> {
        val localDateList = mutableListOf<LocalDateTime>()

        registers.forEach { register ->
            localDateList.add(register.register)
        }

        return localDateList.toList()
    }
}