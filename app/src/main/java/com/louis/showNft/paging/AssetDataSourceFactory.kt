package com.louis.showNft.paging

import android.util.Log
import androidx.paging.DataSource
import com.louis.showNft.api.Api
import com.louis.showNft.model.Asset
import kotlinx.coroutines.CoroutineScope

class AssetDataSourceFactory(
    private val api: Api,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, Asset>() {

    override fun create(): DataSource<Int, Asset> {
        Log.d("[Paging]", "create data source")
        return AssetDataSource(api, scope)
    }
}
