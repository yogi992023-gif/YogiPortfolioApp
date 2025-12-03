package com.yogi.portfolio.portfolio.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.logging.Logger
import com.yogi.portfolio.R
import com.yogi.portfolio.databinding.FragmentCartBinding
import com.yogi.portfolio.databinding.FragmentProductBinding
import com.yogi.portfolio.portfolio.Adapter.CartAdapter
import com.yogi.portfolio.portfolio.ViewModel.CartViewModel
import com.yogi.portfolio.portfolio.data.API.RoomEntity.CartEntity
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.logging.HttpLoggingInterceptor
import kotlin.math.roundToInt

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null // Nullable backing property
    private val binding get() = _binding!!
    private val cartViewModel : CartViewModel by viewModels()
    private lateinit var cartAdapter : CartAdapter
    private var cartList = mutableListOf<CartEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentCartBinding.inflate(inflater, container, false)


        setupRecyclerView()
        observeCart()
        checkoutAction()
        return binding.root
    }


    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(
            onPlusClick = { id ->
                cartViewModel.increaseQty(id)
                          },
            onMinusClick = {
                id -> cartViewModel.decreaseQty(id)
                           },
            onDeleteClick = {
                id -> cartViewModel.removeItem(id)
            }
        )

        binding.rvCartItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }
    }

    private fun observeCart() {
        cartViewModel.cartItems.observe(viewLifecycleOwner) { list ->
            Log.e("Cart Fragment" ,  list.toString())
            if (list.isEmpty()) {
                binding.tvEmpty.visibility = View.VISIBLE
                binding.rvCartItems.visibility = View.GONE
            } else {
                binding.tvEmpty.visibility = View.GONE
                binding.rvCartItems.visibility = View.VISIBLE
            }

            cartAdapter.submitList(list)

            // Total calculation
            val total = list.sumOf { it.price * it.quantity }
            binding.tvTotalAmount.text = "Total : ₹ ${total.roundToInt()}"
        }
    }

    private fun checkoutAction() {
        binding.btnCheckout.setOnClickListener {
            Toast.makeText(requireContext(), "Proceeding to Checkout", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_cartFragment_to_checkoutFragment)
        }
    }

    private fun updateTotal(list: List<CartEntity>) {
        val total = list.sumOf { it.price * it.quantity }
        binding.tvTotalAmount.text = "₹ $total"
    }

}