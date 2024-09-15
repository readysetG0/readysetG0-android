package com.readysetgo.readysetgo

import android.content.Context
import android.webkit.JavascriptInterface

class WebAppInterface(private val mContext: Context) {
    @JavascriptInterface
    fun checkGeoPermission() {

    }

    @JavascriptInterface
    fun checkGpsPermission() {

    }
}