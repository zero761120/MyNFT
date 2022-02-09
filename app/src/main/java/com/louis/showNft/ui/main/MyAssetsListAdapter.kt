package com.louis.showNft.ui.main


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.louis.showNft.common.SingleFieldDiffUtils
import com.louis.showNft.databinding.ItemCollectionBinding

class MyAssetsListAdapter(
    private val owner: LifecycleOwner,
    viewModel: MyAssetsViewModel,
    private val onItemClick: (AssetDataViewModel) -> Unit
) : PagedListAdapter<AssetDataViewModel, MyAssetsListAdapter.ViewHolder>(
    SingleFieldDiffUtils { it.id }
) {

    init {
        viewModel.assetList.observe(owner) { list ->
            submitList(list)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemCollectionBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
                .also {
                    it.lifecycleOwner = owner
                }
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.binding.data = item
        holder.binding.setOnItemClick {
            onItemClick(item)
        }
    }

    class ViewHolder(val binding: ItemCollectionBinding) :
        RecyclerView.ViewHolder(binding.root)
}
