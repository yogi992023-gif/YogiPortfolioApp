package com.yogi.portfolio.portfolio.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogi.portfolio.R
import com.yogi.portfolio.databinding.FragmentProductBinding
import com.yogi.portfolio.portfolio.Adapter.ProductAdapter
import com.yogi.portfolio.portfolio.Utils.Resource.ApiResult
import com.yogi.portfolio.portfolio.ViewModel.CartViewModel
import com.yogi.portfolio.portfolio.ViewModel.ProductViewModel
import com.yogi.portfolio.portfolio.ViewModel.WishlistViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null // Nullable backing property
    private val binding get() = _binding!!
    private val viewModel: ProductViewModel by viewModels()
    private val viewModelCart : CartViewModel by viewModels()
    val wishListViewModel : WishlistViewModel by viewModels()
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentProductBinding.inflate(inflater, container, false)

        adapter = ProductAdapter(wishListViewModel,mutableListOf(), { product ->
            val action = ProductFragmentDirections.actionProductFragmentToProductDetailsFragment(product.id)
            findNavController().navigate(action)

        },onAddCartClick = { cart ->
                viewModelCart.addItem(cart)
            Toast.makeText(requireContext(), "Added to cart", Toast.LENGTH_SHORT).show()
        }
        )

        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())

        viewModel.loadProducts()

        lifecycleScope.launchWhenStarted {
            viewModel.productState.collect { state ->
                when (state) {
                    is ApiResult.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is ApiResult.Success -> {
                        binding.progressBar.visibility = View.GONE

                        Log.e("product succs list...",state.data.toString())
                        adapter.updateData(state.data)
                    }

                    is ApiResult.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        Log.e("product list...",state.message)
                    }
                }
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchProducrt(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) viewModel.loadProducts()
                return true
            }
        })

        binding.rvProducts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                if (!rv.canScrollVertically(1)) {
                    viewModel.loadMoreProducts()
                }
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null    // ❗ prevent memory leak
    }
}