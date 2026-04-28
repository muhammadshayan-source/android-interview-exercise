package com.sadapay.live_coding.sdk

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * A mock OkHttp interceptor that intercepts POST requests and returns a fake success response.
 *
 * Use this interceptor when building your OkHttpClient so you don't need a real server:
 *
 * ```kotlin
 * val client = OkHttpClient.Builder()
 *     .addInterceptor(MockInterceptor())
 *     .build()
 * ```
 */

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // Simulate network delay
        Thread.sleep(1_000L)

        val responseBody = """
            {
                "status": "success",
                "message": "Image uploaded successfully"
            }
        """.trimIndent()

        return Response.Builder()
            .code(200)
            .message("OK")
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .body(responseBody.toResponseBody("application/json".toMediaType()))
            .build()
    }
}
