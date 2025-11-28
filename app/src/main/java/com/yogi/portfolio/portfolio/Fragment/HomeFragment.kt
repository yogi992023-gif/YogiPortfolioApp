package com.yogi.portfolio.portfolio.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.yogi.portfolio.R
import com.yogi.portfolio.databinding.FragmentDashboardBinding
import com.yogi.portfolio.databinding.FragmentHomeBinding
import com.yogi.portfolio.portfolio.Utils.Resource.ApiResult
import com.yogi.portfolio.portfolio.ViewModel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null    // ❗ prevent memory leak
    }
}