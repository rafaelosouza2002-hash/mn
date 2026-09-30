package com.bssl.refugekiosk

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AdminReceiver : DeviceAdminReceiver() {
    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        Log.i("RefugeKiosk", "Device Admin Enabled for Refuge Chamber Kiosk")
    }
}
