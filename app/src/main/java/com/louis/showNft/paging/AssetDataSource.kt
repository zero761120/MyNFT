package com.louis.showNft.paging

import android.util.Log
import androidx.paging.PositionalDataSource
import com.louis.showNft.OWNER_ADDRESS
import com.louis.showNft.QUERY_FORMAT
import com.louis.showNft.api.Api
import com.louis.showNft.api.errorHandler.logExceptionHandler
import com.louis.showNft.model.Asset
import com.louis.showNft.model.AssetsList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AssetDataSource(
    private val api: Api,
    private val scope: CoroutineScope
) : PositionalDataSource<Asset>() {

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<Asset>
    ) {
        val startPos = params.requestedStartPosition
        val loadSize = params.requestedLoadSize
        Log.d("[Paging]", "load initial: startPos=$startPos, loadSize=$loadSize")
        scope.launch(logExceptionHandler) {
            loadInternal(loadSize, startPos)?.let {
                callback.onResult(
                    it.assets?.toMutableList() ?: emptyList(),
                    params.requestedStartPosition,
                    it.assets?.size ?: 0
                )
            }
        }
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<Asset>
    ) {
        Log.d(
            "[Paging]",
            "load range: startPos=${params.startPosition}, loadSize=${params.loadSize}"
        )
        scope.launch(logExceptionHandler) {
            loadInternal(params.loadSize, params.startPosition)?.let {
                callback.onResult(it.assets?.toMutableList() ?: emptyList())
            }
        }
    }

    private suspend fun loadInternal(limit: Int, offset: Int): AssetsList? {
        val response = api.getAssets(QUERY_FORMAT, OWNER_ADDRESS, limit, offset)
        Log.d("[Paging]", "response code=${response.code()}")
        return response.body()
    }
}
