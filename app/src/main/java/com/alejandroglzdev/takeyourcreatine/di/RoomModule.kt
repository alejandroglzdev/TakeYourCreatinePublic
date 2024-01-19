package com.alejandroglzdev.takeyourcreatine.di

import android.content.Context
import androidx.room.Room
import com.alejandroglzdev.takeyourcreatine.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val APP_DATABASE_NAME = "creatine_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideUserDataDao(db: AppDatabase) = db.getUserDataDao()

    @Singleton
    @Provides
    fun provideUserRegistersDao(db: AppDatabase) = db.getUserRegistersDao()
}