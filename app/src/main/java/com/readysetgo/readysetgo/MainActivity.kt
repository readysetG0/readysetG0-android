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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

        checkInternetPermission()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            // TODO : 인터넷 권한은 일반권한.. 런타임 요청 필요 없음. 다른 권한 요청에 사용하고 인터넷을 삭제하기
            REQUEST_CODE_INTERNET_PERMISSION -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    initMainWebView()
                } else {
                    Toast.makeText(
                        this,
                        resources.getString(R.string.internet_permission_needed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            PermissionRequestCode.GEO.code -> {

            }

            PermissionRequestCode.GPS.code -> {

            }
            else -> {
                // 다른 요청 무시
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initMainWebView() {
        webAppInterface = WebAppInterface(this)

        binding.mainWebView.apply {
            mainWebView = this
            settings.javaScriptEnabled = true
            addJavascriptInterface(webAppInterface, "Android")

            webChromeClient = MainWebChromeClient(webAppInterface)

            loadUrl(BuildConfig.WEBVIEW_URL)
        }
    }

    private fun checkInternetPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET
            ) == PackageManager.PERMISSION_GRANTED -> {
                initMainWebView()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.INTERNET
            ) -> {
                showRequestPermissionRationale()
            }
            else -> {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.INTERNET),
                    REQUEST_CODE_INTERNET_PERMISSION)
            }
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

    companion object {
        const val REQUEST_CODE_INTERNET_PERMISSION = 200
    }
}