package com.sadapay.live_coding.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Pre-configured API client wired with [MockInterceptor].
 *
 * Usage:
 * ```kotlin
 * val api = ApiClient.imageUploadApi
 * ```
 */
object ApiClient {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(MockInterceptor())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://localhost/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val imageUploadApi: ImageUploadApi = retrofit.create(ImageUploadApi::class.java)
}
