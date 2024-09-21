package com.readysetgo.readysetgo

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.readysetgo.readysetgo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainWebView: WebView
    private lateinit var webAppInterface: WebAppInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initMainWebView()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PermissionRequestCode.GPS.code -> {
                val isPermissionGranted =
                    grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED

                mainWebView.evaluateJavascript(
                    "window.locationPermissionResult($isPermissionGranted)",
                    null
                )
            }

            else -> {
                // 다른 요청 무시
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initMainWebView() {
        webAppInterface = WebAppInterface(this, this)

        binding.mainWebView.apply {
            mainWebView = this
            settings.javaScriptEnabled = true
            addJavascriptInterface(webAppInterface, "Android")

//            webChromeClient = MainWebChromeClient(webAppInterface)

            loadUrl(BuildConfig.WEBVIEW_URL)
        }
    }

    private fun showRequestPermissionRationale() {
        AlertDialog.Builder(this).apply {
            setMessage(resources.getString(R.string.internet_permission_needed))
            setNegativeButton(resources.getString(R.string.cancel)) { _, _ ->
                finish()
            }
            setPositiveButton(resources.getString(R.string.agree)) { _, _ ->

            }

        }
    }
}