package com.sadapay.live_coding.sdk

fun interface OnImageCaptureSuccess {
    fun onSuccess(imagePath: String)
}

fun interface OnImageCaptureError {
    fun onError(errorMessage: String)
}
