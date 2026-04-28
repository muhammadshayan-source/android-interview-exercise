# Live Coding Exercise — Android Image Capture & Upload

## Overview

You are given a pre-built **Image Capture SDK** that simulates capturing an image and returning a file path. Your task is to integrate this SDK into the app using **clean architecture**, display the captured image, and upload it to a (mock) server.

The project already includes all necessary dependencies and a mock network interceptor — you just need to build the architecture and UI.

---

## The SDK

The SDK lives in `com.sadapay.live_coding.sdk` and is already part of the project.

### Usage

```kotlin
val sdk = ImageCaptureSDKFactory.create(context, licenseKey)

sdk.captureImage(
    onSuccess = { imagePath ->
        // imagePath is the absolute path to the captured image file
    },
    onError = { errorMessage ->
        // errorMessage describes what went wrong
    }
)
```

- `ImageCaptureSDKFactory.create(context, licenseKey)` returns an SDK instance
- The SDK **requires a valid license key** — it will return an error via `onError` if the key is blank
- `captureImage(...)` asynchronously copies an image from assets to internal storage and returns the file path via callback
- The callback is delivered on the **main thread** after a short delay

## Upload API (Pre-built)

A complete Retrofit API is provided and ready to use. It is backed by a `MockInterceptor` that returns a fake success response after a 1-second delay — no real server needed.

### Available classes in `com.sadapay.live_coding.sdk`:

**`ImageUploadApi`** — Retrofit interface with a multipart upload endpoint:
```kotlin
interface ImageUploadApi {
    @Multipart
    @POST("upload")
    suspend fun uploadImage(@Part image: MultipartBody.Part): UploadResponse
}
```

**`ApiClient`** — Pre-configured singleton with OkHttp + MockInterceptor + Retrofit:
```kotlin
val api = ApiClient.imageUploadApi
```

**`UploadResponse`** — Response model:
```kotlin
data class UploadResponse(val status: String, val message: String)
```

### Example — uploading a file:
```kotlin
val file = File(imagePath)
val requestBody = file.asRequestBody("image/jpeg".toMediaType())
val part = MultipartBody.Part.createFormData("image", file.name, requestBody)

val response = ApiClient.imageUploadApi.uploadImage(part)
// response.status == "success"
// response.message == "Image uploaded successfully"
```

---

## What You Need to Build

Create a complete feature using clean architecture layers. The exact naming and package structure is up to you, but your solution should include:

### 1. SDK Manager / Wrapper
- Wraps `ImageCaptureSDKFactory` and `ImageCaptureSDK`
- Isolates the SDK's callback-based API from the rest of your app
- Consider converting callbacks to coroutines

### 2. Repository
- Uses `ApiClient.imageUploadApi` to upload the image
- Returns the result to upper layers

### 3. Use Case
- Orchestrates the upload logic
- Takes the image file path and triggers the upload through the repository

### 4. ViewModel
- Manages UI state across the full flow
- States to handle: **Idle → Loading → Success / Error**
- Exposes state for Compose to observe

### 5. Compose UI Screen
- **Capture button** — triggers image capture via the SDK
- **Image preview** — displays the captured image (use Coil)
- **Upload button** — uploads the captured image
- **State feedback** — show loading indicators, success messages, and error messages

Wire your screen into `MainActivity.kt` (replace the TODO comment).

---

## Available Dependencies

All of these are already added to the project:

| Library | Purpose |
|---------|---------|
| **Retrofit 2.11** | HTTP client / API calls |
| **Converter-Gson** | JSON serialization |
| **OkHttp 4.12** | HTTP engine + interceptors |
| **Coil Compose 2.7** | Image loading in Compose |
| **Lifecycle ViewModel Compose** | ViewModel integration with Compose |
| **Kotlinx Coroutines** | Async programming |
| **Hilt 2.51** | Dependency injection |
| **Hilt Navigation Compose** | `hiltViewModel()` in Compose |
| **KSP** | Annotation processing for Hilt |

---

## Evaluation Criteria

1. **Clean Architecture** — Proper separation of concerns across layers
2. **State Management** — Correct handling of UI states (loading, success, error)
3. **Jetpack Compose** — Idiomatic Compose UI with proper state observation
4. **Coroutine Usage** — Clean async handling, proper scope management
5. **Error Handling** — Graceful handling of failures at each layer
6. **Secrets Handling** — Proper handling of the SDK license key (not hardcoded, uses BuildConfig, understands local.properties pattern)
7. **Code Quality** — Readable, well-structured Kotlin code

---

## Getting Started

1. Open `MainActivity.kt` — you'll see a `// TODO: Implement your screen here` comment
2. Create your package structure and architecture files
3. Build each layer from the bottom up (SDK wrapper → Repository → UseCase → ViewModel → UI)
4. Run the app and verify the full flow works

Good luck!
