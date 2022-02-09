package com.louis.showNft.ui.main

import androidx.lifecycle.ViewModel
import com.louis.showNft.model.Asset

class AssetDataViewModel(
    private val model: Asset
) : ViewModel() {

    val id = model.id
    val imageUrl = model.image_url
    val name = model.name
    val tokenId = model.token_id
    val contractAddress = model.asset_contract?.address.orEmpty()

    override fun equals(other: Any?): Boolean {
        return (other is AssetDataViewModel) && (model == other.model)
    }

    override fun hashCode() = model.hashCode()

}
