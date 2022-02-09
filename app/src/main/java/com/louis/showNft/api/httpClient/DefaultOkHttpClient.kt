package com.louis.showNft.api.httpClient

import com.louis.showNft.api.interceptor.ApiHeaderInterceptor
import com.louis.showNft.api.interceptor.httpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

const val API_TIMEOUT_MILLISECONDS = 20000L

fun createClient() = OkHttpClient.Builder()
    .connectTimeout(API_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
    .readTimeout(API_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
    .writeTimeout(API_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
    .addInterceptor(httpLoggingInterceptor)
    .addInterceptor(ApiHeaderInterceptor())
    .build()
