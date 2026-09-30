package com.bssl.refugekiosk

import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.webkit.JavascriptInterface
import java.io.File
import java.util.Locale

import androidx.appcompat.app.AppCompatActivity

/**
 * JavaScript interface exposed to the WebView as "window.AndroidBridge".
 * Handles offline voice playback (via embedded audio assets & TTS)
 * and hidden telemetry recording for mining safety drill analysis.
 */
class WebAppInterface(private val activity: AppCompatActivity) : TextToSpeech.OnInitListener {
    private val context: Context get() = activity
    private var tts: TextToSpeech? = TextToSpeech(activity, this)
    private var isTtsReady = false
    private var mediaPlayer: MediaPlayer? = null

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale("pt", "BR"))
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.w("RefugeKiosk", "pt-BR TTS offline data missing, will rely on embedded audio files")
            } else {
                tts?.setSpeechRate(0.95f)
                tts?.setPitch(1.0f)
                isTtsReady = true
                Log.i("RefugeKiosk", "Native pt-BR TTS successfully initialized")
            }
        } else {
            Log.e("RefugeKiosk", "TTS initialization error code: $status")
        }
    }

    /**
     * Plays pre-recorded crystal-clear broadcast audio files from assets/audio/
     * (standby, step1, step2, step3, step4, step5, complete).
     * 100% offline, zero network, zero external engine dependencies.
     */
    @JavascriptInterface
    fun playAudio(audioName: String): Boolean {
        return try {
            stop()
            val afd = context.assets.openFd("audio/$audioName.m4a")
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .setUsage(AudioAttributes.USAGE_ASSISTANCE_NAVIGATION_GUIDANCE)
                        .build()
                )
                setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                afd.close()
                prepare()
                start()
            }
            Log.i("RefugeKiosk", "Playing embedded audio: $audioName.m4a")
            true
        } catch (e: Exception) {
            Log.e("RefugeKiosk", "Failed to play embedded audio $audioName, falling back to TTS", e)
            false
        }
    }

    @JavascriptInterface
    fun speak(text: String) {
        if (isTtsReady && tts != null) {
            tts?.stop()
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "RefugeStepUtterance")
        } else {
            Log.w("RefugeKiosk", "TTS not ready or failed to speak: $text")
        }
    }

    @JavascriptInterface
    fun stop() {
        try {
            mediaPlayer?.let {
                if (it.isPlaying) {
                    it.stop()
                }
                it.reset()
                it.release()
            }
            mediaPlayer = null
        } catch (e: Exception) {
            Log.w("RefugeKiosk", "Error stopping MediaPlayer", e)
        }
        tts?.stop()
    }

    /**
     * Saves telemetry data silently to persistent storage for auditing.
     * Can be extracted via ADB: adb pull /sdcard/kiosk_telemetry.jsonl
     */
    @JavascriptInterface
    fun recordTelemetry(jsonData: String) {
        try {
            // Internal app storage
            val internalFile = File(context.filesDir, "telemetry.jsonl")
            internalFile.appendText(jsonData + "\n")

            // Public storage for easy adb pull
            val publicFile = File("/sdcard/kiosk_telemetry.jsonl")
            publicFile.appendText(jsonData + "\n")

            Log.i("RefugeTelemetry", "Telemetry recorded: $jsonData")
        } catch (e: Exception) {
            Log.e("RefugeTelemetry", "Error saving telemetry", e)
        }
    }

    @JavascriptInterface
    fun getTelemetry(): String {
        return try {
            val file = File(context.filesDir, "telemetry.jsonl")
            if (file.exists()) file.readText() else "[]"
        } catch (e: Exception) {
            "[]"
        }
    }

    @JavascriptInterface
    fun clearTelemetry(): Boolean {
        return try {
            File(context.filesDir, "telemetry.jsonl").delete()
            File("/sdcard/kiosk_telemetry.jsonl").delete()
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Opens native Android Bluetooth Pairing Settings so admin can connect speakers.
     * Temporarily pauses LockTask so Android allows the Settings activity to open,
     * which automatically re-locks into Kiosk Mode as soon as MainActivity resumes.
     */
    @JavascriptInterface
    fun openBluetoothSettings() {
        activity.runOnUiThread {
            try {
                try {
                    activity.stopLockTask()
                } catch (e: Exception) {
                    Log.w("RefugeKiosk", "stopLockTask exception: ${e.message}")
                }
                val intent = Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                activity.startActivity(intent)
                Log.i("RefugeKiosk", "Opening Android Bluetooth Settings with LockTask paused")
            } catch (e: Exception) {
                Log.e("RefugeKiosk", "Failed to open Bluetooth settings", e)
            }
        }
    }

    fun shutdown() {
        stop()
        tts?.shutdown()
        tts = null
    }
}
