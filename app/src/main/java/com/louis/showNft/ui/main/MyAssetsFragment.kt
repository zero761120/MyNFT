package com.louis.showNft.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.louis.showNft.databinding.MyAssetsFragmentBinding

class MyAssetsFragment : Fragment() {

    private lateinit var viewDataBinding: MyAssetsFragmentBinding
    private val viewModel by viewModels<MyAssetsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = MyAssetsFragmentBinding.inflate(inflater, container, false)
        .also {
            viewDataBinding = it
            it.lifecycleOwner = this.viewLifecycleOwner
            it.viewModel = viewModel.apply {
                navController = findNavController()
            }
        }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val myAssetsListAdapter = MyAssetsListAdapter(
            viewLifecycleOwner,
            viewModel,
            onItemClick = {
                viewModel.navigateToDetail(it)
            }
        )

        viewDataBinding.recyclerView.apply {
            adapter = myAssetsListAdapter
        }

        viewModel.getBalance()
    }
}
