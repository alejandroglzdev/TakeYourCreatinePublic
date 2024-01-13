package com.alejandroglzdev.takeyourcreatine.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = "user_registers")
data class UserRegisters (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "register") val register: LocalDateTime
)
