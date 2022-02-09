package com.louis.showNft.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.louis.showNft.databinding.DetailsFragmentBinding
import com.louis.showNft.navigateToBrowser

class DetailsFragment : Fragment() {

    private lateinit var viewDataBinding: DetailsFragmentBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        DetailsFragmentBinding.inflate(inflater, container, false)
            .also {
                viewDataBinding = it
                it.lifecycleOwner = this.viewLifecycleOwner
                it.viewModel = viewModel.apply {
                    navController = findNavController()
                }
            }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            doOnBrowser = {
                requireActivity().navigateToBrowser(it)
            }
            getAssetDetails(args.contractAddress.orEmpty(), args.tokenId.orEmpty())
        }
    }
}
