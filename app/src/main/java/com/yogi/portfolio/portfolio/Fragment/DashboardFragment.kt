package com.yogi.portfolio.portfolio.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogi.portfolio.R
import com.yogi.portfolio.databinding.FragmentDashboardBinding
import com.yogi.portfolio.databinding.FragmentHomeBinding
import com.yogi.portfolio.portfolio.Adapter.ProductAdapter
import com.yogi.portfolio.portfolio.Utils.Resource.ApiResult
import com.yogi.portfolio.portfolio.ViewModel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.logging.Logger
import kotlin.getValue

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentDashboardBinding.inflate(inflater,container,false)

        val action = DashboardFragmentDirections.actionDashboardFragmentToProductFragment()
        findNavController().navigate(action)

     //   findNavController().navigate(R.id.action_dashboardFragment_to_productFragment)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null    // ❗ prevent memory leak
    }
}