package com.louis.showNft.common

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class SingleFieldDiffUtils<T>(val fieldExtractor: (T) -> Any?) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) =
        fieldExtractor(oldItem) == fieldExtractor(newItem)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem

}
