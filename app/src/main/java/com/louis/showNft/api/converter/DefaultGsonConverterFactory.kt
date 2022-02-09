package com.louis.showNft.api.converter

import com.louis.showNft.api.serialization.defaultGson
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

val defaultGsonConverterFactory: Converter.Factory by lazy {
    GsonConverterFactory.create(defaultGson)
}
