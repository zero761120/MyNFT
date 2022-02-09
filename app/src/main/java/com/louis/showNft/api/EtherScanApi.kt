package com.louis.showNft.api

import com.louis.showNft.api.converter.StringConverterFactory
import com.louis.showNft.ETHER_SCAN_BASE_URL
import com.louis.showNft.api.converter.defaultGsonConverterFactory
import com.louis.showNft.api.httpClient.createClient
import com.louis.showNft.model.BalanceModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface EthScanApi {

    @GET("api")
    suspend fun getBalance(
        @Query("module") module: String,
        @Query("action") action: String,
        @Query("address") address: String,
        @Query("tag") tag: String,
        @Query("apikey") apikey: String
    ): Response<BalanceModel>
}

val etherScanApi: EthScanApi
    get() = Retrofit.Builder()
        .baseUrl(ETHER_SCAN_BASE_URL)
        .addConverterFactory(StringConverterFactory())
        .addConverterFactory(defaultGsonConverterFactory)
        .client(createClient())
        .build()
        .create(EthScanApi::class.java)
