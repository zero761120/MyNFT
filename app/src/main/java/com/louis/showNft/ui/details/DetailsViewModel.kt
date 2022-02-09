package com.louis.showNft.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.louis.showNft.api.Api
import com.louis.showNft.api.defaultApi
import com.louis.showNft.api.errorHandler.logExceptionHandler
import com.louis.showNft.model.AssetDetail
import kotlinx.coroutines.launch

class DetailsViewModel(private val api: Api = defaultApi) : ViewModel() {

    lateinit var navController: NavController
    lateinit var doOnBrowser: (url: String) -> Unit
    private val _detailsResponse = MutableLiveData<AssetDetail>()
    val detailsResponse: LiveData<AssetDetail> = _detailsResponse

    fun goBack() {
        navController.popBackStack()
    }

    fun getAssetDetails(contractAddress: String, tokenId: String) {
        if (contractAddress.isNotEmpty() && tokenId.isNotEmpty()) {
            viewModelScope.launch(logExceptionHandler) {
                _detailsResponse.value = api.getAssetDetails(contractAddress, tokenId).body()
            }
        }
    }

    fun goPermalink(webUrl: String?) {
        if (!webUrl.isNullOrEmpty()) doOnBrowser(webUrl)
    }
}
