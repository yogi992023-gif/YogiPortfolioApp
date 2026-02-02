package com.yogi.portfolio.portfolio.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.messaging.FirebaseMessaging
import com.yogi.portfolio.R
import com.yogi.portfolio.databinding.FragmentCheckoutBinding
import com.yogi.portfolio.portfolio.Service.MyFirebaseService
import com.yogi.portfolio.portfolio.Utils.CommonNotification
import com.yogi.portfolio.portfolio.ViewModel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null // Nullable backing property
    private val binding get() = _binding!!
    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding =  FragmentCheckoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartViewModel.cartItems.observe(viewLifecycleOwner){ list ->
            var total = list.sumOf { it.price * it.quantity }
            binding.tvCheckoutTotal.text = "Total : ₹ ${total.roundToInt()}"
        }

        binding.btnProceedPayment.setOnClickListener {
            FirebaseMessaging.getInstance().token
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.e("FCM_TOKEN", "Fetching token failed", task.exception)
                        return@addOnCompleteListener
                    }

                    val token = task.result
                    Log.d("FCM_TOKEN", token)
                }

            CommonNotification.show(
                requireContext(),
                "Info",
                "Profile updated"
            )

            findNavController().navigate(R.id.action_checkoutFragment_to_paymentFrgament)
        }
    }


}