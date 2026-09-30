package com.bssl.refugekiosk

import android.annotation.SuppressLint
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private var webAppInterface: WebAppInterface? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Lock to Landscape Orientation for industrial panel mount
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE

        // 2. Keep Screen Always On (never sleep or dim during emergency)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // 3. Immersive sticky fullscreen
        setupImmersiveFullscreen()

        // 4. Intercept Back Button to prevent accidental exit
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Do nothing: stay locked in kiosk mode
            }
        })

        // 5. Initialize WebView
        webView = WebView(this).apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                allowFileAccess = true
                allowContentAccess = true
                mediaPlaybackRequiresUserGesture = false
                cacheMode = WebSettings.LOAD_NO_CACHE
                useWideViewPort = true
                loadWithOverviewMode = true
                builtInZoomControls = false
                displayZoomControls = false
            }
            isVerticalScrollBarEnabled = false
            isHorizontalScrollBarEnabled = false
            overScrollMode = View.OVER_SCROLL_NEVER
            setBackgroundColor(0xFF0C0D10.toInt()) // Matches console mine-bg
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
        }

        // 6. Connect Native TTS & Audio Bridge
        webAppInterface = WebAppInterface(this)
        webView.addJavascriptInterface(webAppInterface!!, "AndroidBridge")

        // 7. Load local offline assets
        webView.loadUrl("file:///android_asset/index.html")

        setContentView(webView)

        // 8. Enforce LockTask / Kiosk Mode
        enforceKioskLock()
    }

    override fun onResume() {
        super.onResume()
        setupImmersiveFullscreen()
        enforceKioskLock()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            setupImmersiveFullscreen()
        } else {
            // Block and collapse notification shade pull-down
            val closeDialog = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
            sendBroadcast(closeDialog)
            try {
                @SuppressLint("WrongConstant")
                val statusBarService = getSystemService("statusbar")
                val statusBarManager = Class.forName("android.app.StatusBarManager")
                val collapse = statusBarManager.getMethod("collapsePanels")
                collapse.invoke(statusBarService)
            } catch (e: Exception) {
                // Ignore if not accessible
            }
        }
    }

    // Intercept hardware keys: Back, Overview/Recent Apps ("quadradinho"), and Menu
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK ||
            keyCode == KeyEvent.KEYCODE_APP_SWITCH ||
            keyCode == KeyEvent.KEYCODE_MENU ||
            keyCode == KeyEvent.KEYCODE_WINDOW) {
            return true // Consume and block exiting
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_APP_SWITCH ||
            event.keyCode == KeyEvent.KEYCODE_BACK) {
            return true // Consume and block recent apps
        }
        return super.dispatchKeyEvent(event)
    }

    private fun enforceKioskLock() {
        try {
            val dpm = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
            val adminComponent = ComponentName(this, AdminReceiver::class.java)

            if (dpm.isDeviceOwnerApp(packageName)) {
                // Whitelist package for LockTask (pins silently with ZERO unpin option)
                dpm.setLockTaskPackages(adminComponent, arrayOf(packageName))
                // Disable status bar expansion completely
                dpm.setStatusBarDisabled(adminComponent, true)
                // Disable lock screen/keyguard
                dpm.setKeyguardDisabled(adminComponent, true)
                Log.i("RefugeKiosk", "Device Owner Kiosk Mode configured successfully")
            }

            // Start LockTask mode (disables Recent Apps / "quadradinho")
            startLockTask()
        } catch (e: Exception) {
            Log.w("RefugeKiosk", "Kiosk lock enforcement note: ${e.message}")
        }
    }

    private fun setupImmersiveFullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController
            if (controller != null) {
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            )
        }
    }

    override fun onDestroy() {
        webAppInterface?.shutdown()
        webView.destroy()
        super.onDestroy()
    }
}
