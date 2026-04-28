package com.sadapay.live_coding.sdk

import com.google.gson.annotations.SerializedName

data class UploadResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)
