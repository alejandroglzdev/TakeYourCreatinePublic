package com.alejandroglzdev.takeyourcreatine.core

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.alejandroglzdev.takeyourcreatine.CreatineViewModel
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserRegisters
import com.alejandroglzdev.takeyourcreatine.data.notifications.NotificationPermissionTextProvider
import com.alejandroglzdev.takeyourcreatine.ui.component.items.PermissionDialog
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
    @Composable
    fun a(creatineViewModel: CreatineViewModel) {
        val context = LocalContext.current as Activity
        val permissionsToRequest = arrayOf(
            Manifest.permission.POST_NOTIFICATIONS
        )

        val dialogQueue = creatineViewModel.visiblePermissionDialogQueue
        val notificationPermissionResultLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                creatineViewModel.onPermissionResult(
                    permission = Manifest.permission.POST_NOTIFICATIONS,
                    isGranted = isGranted
                )
            }
        )

        val multiplePermissionResultLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = { perms ->
                permissionsToRequest.forEach {permission ->
                    creatineViewModel.onPermissionResult(
                        permission = permission,
                        isGranted = perms[permission] == true
                    )

                }
            }
        )

        LaunchedEffect(Unit) {
            notificationPermissionResultLauncher.launch(
                Manifest.permission.POST_NOTIFICATIONS
            )
        }

        dialogQueue
            .reversed()
            .forEach { permission ->
                PermissionDialog(
                    permissionTextProvider = when (permission) {
                        Manifest.permission.POST_NOTIFICATIONS -> NotificationPermissionTextProvider()
                        else -> return@forEach
                    },
                    isPermanentlyDeclined = true
                    ,
                    onDismiss = { creatineViewModel.dismissDialog() },
                    onOkClick = {
                        creatineViewModel.dismissDialog()
                        multiplePermissionResultLauncher.launch(
                            arrayOf(permission)
                        )
                    },
                    onGoToAppSettingsClick = {
                        val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", context.packageName, null)
                        )
                        //FUNCIONA!!
                        context.startActivity(intent)
                    })
            }
    }
}