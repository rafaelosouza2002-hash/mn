package com.bssl.refugekiosk

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import android.webkit.JavascriptInterface
import java.util.Locale

/**
 * JavaScript interface exposed to the WebView as "window.AndroidBridge".
 * Provides robust native offline Text-to-Speech (TTS) for mining safety protocols.
 */
class WebAppInterface(private val context: Context) : TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = TextToSpeech(context, this)
    private var isTtsReady = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale("pt", "BR"))
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.w("RefugeKiosk", "pt-BR language pack missing or not supported in device TTS")
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
        tts?.stop()
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}
