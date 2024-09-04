package com.readysetgo.readysetgo

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.webkit.PermissionRequest
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.readysetgo.readysetgo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainWebView: WebView
    private val REQUEST_CODE_INTERNET_PERMISSION = 0

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

        checkInternetPermission()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE_INTERNET_PERMISSION -> {
                if ((grantResults.isNotEmpty()
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    initMainWebView()
                } else {
                    Toast.makeText(
                        this,
                        resources.getString(R.string.internet_permission_needed),
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            } else -> {
                // 다른 요청 무시
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initMainWebView() {
        binding.mainWebView.apply {
            mainWebView = this
            settings.javaScriptEnabled = true
//            webViewClient = MainWebViewClient()
            loadUrl(resources.getString(R.string.webview_url))
        }
    }

    private fun checkInternetPermission() {
        // TODO : 인터넷 권한 미허용시 동작 고민.. (앱 종료 OR native 에러 페이지?)
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                    == PackageManager.PERMISSION_GRANTED -> {
                        initMainWebView()
                    }
            else -> {
                requestPermissions(arrayOf(Manifest.permission.INTERNET), REQUEST_CODE_INTERNET_PERMISSION)
            }
        }
    }

    // TODO : WebViewClient, WebChromeClient 추가
//    inner class MainWebViewClient : WebViewClient() {
//
//    }

}