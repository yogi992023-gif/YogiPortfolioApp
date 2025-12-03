package com.yogi.portfolio.portfolio.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.yogi.portfolio.R
import com.yogi.portfolio.databinding.FragmentDashboardBinding
import com.yogi.portfolio.portfolio.Adapter.DashboardAdapter
import com.yogi.portfolio.portfolio.ViewModel.CartViewModel
import com.yogi.portfolio.portfolio.ViewModel.MenuViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DashboardAdapter
    private val viewModel : MenuViewModel by viewModels()
    private val cartViewModel : CartViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentDashboardBinding.inflate(inflater,container,false)

        // insert sample menu
        /*viewModel.addMenu("Profile", R.drawable.ic_profile)
        viewModel.addMenu("Settings", R.drawable.ic_settings)*/

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* lifecycleScope.launch {
            if (viewModel.menus.value.isNullOrEmpty()) {
                viewModel.addMenu("Product List", R.drawable.ic_product_list)
            }
        }*/
        MobileAds.initialize(requireContext())

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        adapter = DashboardAdapter(emptyList()) { menu ->
            Toast.makeText(requireContext(), menu.menuName, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_dashboardFragment_to_productFragment)

        }

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = adapter

        // observe live data from room

        viewModel.menus.observe(viewLifecycleOwner) { it ->
            adapter.updateData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null    // ❗ prevent memory leak
    }
}