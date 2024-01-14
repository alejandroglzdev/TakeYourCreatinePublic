package com.alejandroglzdev.takeyourcreatine.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class UserData(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "onboard") val onboard: Boolean? = false,
    @ColumnInfo(name = "notifications") val notifications: Boolean? = null,
    @ColumnInfo(name = "creatine_intake") val creatineIntake: Int?
)