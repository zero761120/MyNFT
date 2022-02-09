package com.louis.showNft.common

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class GlideModule : AppGlideModule() {
    companion object {
        const val CACHE_SIZE_BYTES = 94371840L // 90 MB
        const val CACHE_FOLDER_NAME = "Images"
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.apply {
            val requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            setDiskCache(InternalCacheDiskCacheFactory(context, CACHE_FOLDER_NAME, CACHE_SIZE_BYTES))
            setDefaultRequestOptions(requestOptions)
        }
    }
}
