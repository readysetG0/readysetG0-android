package com.readysetgo.readysetgo

import android.content.Context
import android.webkit.JavascriptInterface

class WebAppInterface(private val mContext: Context) {
    @JavascriptInterface
    fun checkGeoPermission(): Boolean {
        // TODO : Geo 권한 확인 구현
        return true
    }

    @JavascriptInterface
    fun requestGeoPermission() {
        // TODO : Geo 권한 요청 구현
    }

    @JavascriptInterface
    fun checkGpsPermission(): Boolean {
        // TODO : Gps 권한 확인 구현
        return true
    }

    @JavascriptInterface
    fun requestGpsPermission() {
        // TODO : Gps 권한 요청 구현
    }
}