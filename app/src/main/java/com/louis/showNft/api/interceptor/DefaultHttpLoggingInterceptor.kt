package com.louis.showNft.api.interceptor

import com.louis.showNft.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

val httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
    .apply {
        this.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
    }
