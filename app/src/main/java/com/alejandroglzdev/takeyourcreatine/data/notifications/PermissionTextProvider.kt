package com.alejandroglzdev.takeyourcreatine.data.notifications

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}