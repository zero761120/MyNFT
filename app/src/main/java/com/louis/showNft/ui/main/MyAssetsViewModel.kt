package com.louis.showNft.ui.main

import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.louis.showNft.ETHER_SCAN_API_KEY
import com.louis.showNft.OWNER_ADDRESS
import com.louis.showNft.R
import com.louis.showNft.api.*
import com.louis.showNft.api.errorHandler.logExceptionHandler
import com.louis.showNft.model.BalanceModel
import com.louis.showNft.navigateForward
import com.louis.showNft.paging.AssetDataSourceFactory
import com.louis.showNft.paging.DataSourceConverterFactory
import kotlinx.coroutines.launch

class MyAssetsViewModel(
    api: Api = defaultApi,
    private val ethApi: EthScanApi = etherScanApi
) : ViewModel() {
    companion object {
        const val ITEM_SIZE = 20
    }

    lateinit var navController: NavController

    //paging data source
    private val dataSourceFactory = AssetDataSourceFactory(api, viewModelScope)
    val assetList: LiveData<PagedList<AssetDataViewModel>>
    val isLoading = MutableLiveData<Boolean>()

    //if need show empty view could use it
    val isEmpty = MutableLiveData<Boolean>()

    private val _balanceResponse = MutableLiveData<BalanceModel>()
    val balanceResponse: LiveData<BalanceModel> = _balanceResponse

    init {
        isLoading.value = true
        assetList = LivePagedListBuilder(
            DataSourceConverterFactory(dataSourceFactory) { model ->
                AssetDataViewModel(model)
            },
            PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(ITEM_SIZE)
                .setPageSize(ITEM_SIZE)
                .build()

        )
            .setBoundaryCallback(object : PagedList.BoundaryCallback<AssetDataViewModel>() {
                override fun onItemAtFrontLoaded(itemAtFront: AssetDataViewModel) {
                    isLoading.value = false
                }

                override fun onZeroItemsLoaded() {
                    isEmpty.value = true
                }
            })
            .build()
    }

    fun getBalance() {
        viewModelScope.launch(logExceptionHandler) {
            _balanceResponse.value = ethApi.getBalance(
                module = "account",
                action = "balance",
                address = OWNER_ADDRESS,
                tag = "latest",
                apikey = ETHER_SCAN_API_KEY
            ).body()
        }
    }

    fun navigateToDetail(data: AssetDataViewModel) {
        navController.navigateForward(
            R.id.detailsFragment,
            bundleOf("contract_address" to data.contractAddress, "token_id" to data.tokenId)
        )
    }

//    /**
//     * if need refresh page could use it
//     */
//    fun refreshList() {
//        isEmpty.value = false
//        assetList.value?.dataSource?.invalidate()
//    }
}
