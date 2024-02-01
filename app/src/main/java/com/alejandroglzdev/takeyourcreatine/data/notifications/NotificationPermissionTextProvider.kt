package com.alejandroglzdev.takeyourcreatine.data.notifications

class NotificationPermissionTextProvider : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined notifications permissions. You can go to the app " +
                    "settings to grant it."
        } else {
            "This application needs permissions on notifications so it can remind you to take your " +
                    "creatine."
        }
    }
}