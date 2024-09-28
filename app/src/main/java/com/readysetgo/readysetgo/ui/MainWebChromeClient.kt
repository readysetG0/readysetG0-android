package com.readysetgo.readysetgo.ui

import android.webkit.GeolocationPermissions
import android.webkit.WebChromeClient

class MainWebChromeClient(private val webAppInterface : WebAppInterface) : WebChromeClient() {
    override fun onGeolocationPermissionsShowPrompt(
        origin: String?,
        callback: GeolocationPermissions.Callback?
    ) {
        super.onGeolocationPermissionsShowPrompt(origin, callback)
    }
}