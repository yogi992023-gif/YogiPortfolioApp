package com.yogi.portfolio.portfolio.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.tooling.preview.AndroidUiMode
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogi.portfolio.databinding.FragmentWishlistBinding
import com.yogi.portfolio.portfolio.Adapter.WishlistAdapter
import com.yogi.portfolio.portfolio.ViewModel.WishlistViewModel
import com.yogi.portfolio.portfolio.data.API.RoomEntity.ProductEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishlistFragment : Fragment() {

    private val viewModel : WishlistViewModel by viewModels()
    private lateinit var adapter: WishlistAdapter

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding =  FragmentWishlistBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = WishlistAdapter { item ->
            viewModel.toggleWishlist(
                ProductEntity(
                    id = item.id,
                    title = item.title,
                    price = item.price,
                    image = item.image,
                    description = "",
                    brand = "",
                    category = ""
                )
            )
        }


        binding.rvWishlist.layoutManager = LinearLayoutManager(requireContext())
        binding.rvWishlist.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.wishlist.collect { list ->
                Log.e("Wish List...",list.toString())
                adapter.submitList(list)
                binding.tvEmptyWishlist.visibility =
                    if (list.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }


}