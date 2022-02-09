package com.louis.showNft.model

data class Asset(
    val asset_contract: AssetContract? = null,
    val description: String? = null,
    val id: Int? = null,
    val image_url: String? = null,
    val name: String? = null,
    val permalink: String? = null,
    val token_id: String? = null,
)