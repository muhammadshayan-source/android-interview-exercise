package com.sadapay.live_coding.sdk

import android.content.Context
import android.os.Handler
import android.os.Looper
import java.io.File
import java.io.IOException

interface ImageCaptureSDK {
    fun captureImage(onSuccess: OnImageCaptureSuccess, onError: OnImageCaptureError)
}

internal class ImageCaptureSDKImpl(
    private val context: Context,
    private val licenseKey: String
) : ImageCaptureSDK {

    override fun captureImage(onSuccess: OnImageCaptureSuccess, onError: OnImageCaptureError) {
        Handler(Looper.getMainLooper()).postDelayed({
            if (licenseKey.isBlank()) {
                onError.onError("Invalid license key. Please provide a valid SDK license key.")
                return@postDelayed
            }

            try {
                val inputStream = context.assets.open("sample_image.jpg")
                val outputFile = File(context.filesDir, "captured_image.jpg")

                inputStream.use { input ->
                    outputFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }

                onSuccess.onSuccess(outputFile.absolutePath)
            } catch (e: IOException) {
                onError.onError("Failed to capture image: ${e.message}")
            }
        }, 500L)
    }
}
