package com.sadapay.live_coding.sdk

import android.content.Context

object ImageCaptureSDKFactory {

    /**
     * Creates an [ImageCaptureSDK] instance.
     *
     * @param context Application context
     * @param licenseKey A valid SDK license key. The SDK will return an error if the key is blank.
     */
    fun create(context: Context, licenseKey: String): ImageCaptureSDK {
        return ImageCaptureSDKImpl(context.applicationContext, licenseKey)
    }
}
