package com.sadapay.live_coding.networking

import com.google.gson.annotations.SerializedName

data class UploadResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)
