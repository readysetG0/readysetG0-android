package com.readysetgo.readysetgo.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.webkit.JavascriptInterface
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.readysetgo.readysetgo.PermissionRequestCode

class WebAppInterface(private val mContext: Context, private val activity: MainActivity) {
    @JavascriptInterface
    fun checkLocationPermission(): Boolean {
        val isCoarseLocationGranted = ContextCompat.checkSelfPermission(
            mContext,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val isFineLocationGranted = ContextCompat.checkSelfPermission(
            mContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        return isCoarseLocationGranted && isFineLocationGranted
    }

    @JavascriptInterface
    fun requestLocationPermission() {
        ActivityCompat.requestPermissions(activity,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION),
            PermissionRequestCode.GPS.code
        )
    }
}