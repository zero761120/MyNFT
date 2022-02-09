package com.louis.showNft.api.serialization

import android.net.Uri
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder

val defaultGson: Gson by lazy {
    GsonBuilder()
        .registerTypeAdapter(
            Uri::class.java,
            UriDeserializer()
        )
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
}
