package com.yogi.portfolio.portfolio.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yogi.portfolio.R
import com.yogi.portfolio.databinding.FragmentCheckoutBinding
import com.yogi.portfolio.databinding.FragmentPaymentBinding
import com.yogi.portfolio.portfolio.ViewModel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue
import kotlin.math.roundToInt


@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null // Nullable backing property
    private val binding get() = _binding!!
    private val cartViewModel: CartViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentPaymentBinding.inflate(inflater, container, false)

        cartViewModel.cartItems.observe(viewLifecycleOwner){ list ->
            var total = list.sumOf { it.price * it.quantity }
            binding.tvCheckoutTotal.text = "Total : ₹ ${total.roundToInt()}"
        }

        binding.btnPayNow.setOnClickListener {
            val selected = binding.rgPayment.checkedRadioButtonId

            if (selected == -1) {
                Toast.makeText(requireContext(), "Select a payment method", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

         //   findNavController().navigate(R.id.action_paymentFragment_to_orderSuccessFragment)
        }

        return binding.root
    }

}