package com.yogi.portfolio.portfolio.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.yogi.portfolio.R
import com.yogi.portfolio.databinding.FragmentProductBinding
import com.yogi.portfolio.databinding.FragmentProductDetailsBinding
import com.yogi.portfolio.portfolio.Utils.CommonProgressDialog
import com.yogi.portfolio.portfolio.ViewModel.ProductDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.observeOn


@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null // Nullable backing property
    private val binding get() = _binding!!
    private val args: ProductDetailsFragmentArgs by navArgs()
    private val viewModel: ProductDetailsViewModel by viewModels()

    private var progressDialog : CommonProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        progressDialog = CommonProgressDialog(requireContext())

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) progressDialog?.show() else progressDialog?.dismiss()
        }


        viewModel.getProduct(args.productId)


        lifecycleScope.launchWhenStarted{
            viewModel.product.collect { item ->
                item?.let {
                    binding.tvTitle.text = it.title
                    binding.tvDesc.text  = it.description
                    binding.tvPrice.text  = "₹${it.price}"
                }
            }
        }

        return binding.root
    }


}