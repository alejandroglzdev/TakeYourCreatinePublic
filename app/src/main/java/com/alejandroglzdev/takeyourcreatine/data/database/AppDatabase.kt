package com.alejandroglzdev.takeyourcreatine.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alejandroglzdev.takeyourcreatine.data.database.dao.UserDataDao
import com.alejandroglzdev.takeyourcreatine.data.database.dao.UserRegistersDao
import com.alejandroglzdev.takeyourcreatine.data.database.entities.Converters
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserData
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserRegisters

@Database(entities = [UserData::class, UserRegisters::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDataDao(): UserDataDao
    abstract fun getUserRegistersDao(): UserRegistersDao
}