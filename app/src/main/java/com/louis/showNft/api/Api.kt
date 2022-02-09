package com.louis.showNft.api

import com.louis.showNft.api.converter.StringConverterFactory
import com.louis.showNft.BASE_URL
import com.louis.showNft.api.converter.defaultGsonConverterFactory
import com.louis.showNft.api.httpClient.createClient
import com.louis.showNft.model.AssetDetail
import com.louis.showNft.model.AssetsList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("api/v1/assets")
    suspend fun getAssets(
        @Query("format") format: String,
        @Query("owner") owner: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<AssetsList>

    @GET("api/v1/asset/{contract_address}/{token_id}")
    suspend fun getAssetDetails(
        @Path("contract_address") contractAddress: String,
        @Path("token_id") tokenId: String
    ): Response<AssetDetail>

}

val defaultApi: Api
    get() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(StringConverterFactory())
        .addConverterFactory(defaultGsonConverterFactory)
        .client(createClient())
        .build()
        .create(Api::class.java)
