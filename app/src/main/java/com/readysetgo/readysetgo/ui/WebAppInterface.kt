package com.readysetgo.readysetgo.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.webkit.JavascriptInterface
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.readysetgo.readysetgo.PermissionRequestCode
import com.readysetgo.readysetgo.data.Trace
import com.readysetgo.readysetgo.data.TraceDatabase
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WebAppInterface(private val mContext: Context, private val activity: MainActivity) {
    // 위치정보 권한 요청
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
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PermissionRequestCode.GPS.code
        )
    }

    // 발자취 정보
    @JavascriptInterface
    fun loadAllTrace(travelInfoId: Long): String {
        return Json.encodeToString(
            TraceDatabase.getInstance(mContext)
                .traceDao()
                .selectAllTrace(travelInfoId)
        )
    }

    @JavascriptInterface
    fun loadTraceByDay(travelInfoId: Long, day: Int): String {
        return Json.encodeToString(
            TraceDatabase.getInstance(mContext)
                .traceDao()
                .selectTraceByDay(travelInfoId, day)
        )
    }

    @JavascriptInterface
    fun saveTrace(traceListJson: String): String {
        val traceList: List<Trace> = Json.decodeFromString(traceListJson)
        val result: List<Long> = TraceDatabase.getInstance(mContext)
            .traceDao()
            .insertTraceList(traceList)

        return Json.encodeToString(result)
    }
}