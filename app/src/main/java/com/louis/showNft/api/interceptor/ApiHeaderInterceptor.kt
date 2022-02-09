package com.louis.showNft.api.interceptor

import com.louis.showNft.HEADER_X_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ApiHeaderInterceptor : Interceptor {

    companion object {
        val headers = mapOf(
            "X-API-KEY" to HEADER_X_API_KEY
        )
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().apply {
            headers.forEach {
                addHeader(it.key, it.value)
            }
        }.build()
        return chain.proceed(request)
    }
}
