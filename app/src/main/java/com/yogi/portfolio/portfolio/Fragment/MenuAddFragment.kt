package com.yogi.portfolio.portfolio.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yogi.portfolio.databinding.FragmentMenuAddBinding
import com.yogi.portfolio.portfolio.ViewModel.MenuViewModel
import com.yogi.portfolio.portfolio.ui.composable.MenuInputScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MenuAddFragment : Fragment() {

    val menuViewModel : MenuViewModel by viewModels()
    private var _binding: FragmentMenuAddBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding =  FragmentMenuAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeInputContainer.setContent {

            binding.composeInputContainer.setContent {
                MenuInputScreen(
                    onSubmit = { title, iconRes ->
                        Toast.makeText(requireContext(), "Saved: $title", Toast.LENGTH_LONG).show()
                        menuViewModel.addMenu(title, iconRes)
                        findNavController().navigate(com.yogi.portfolio.R.id.action_addmenu_to_dashboardFragment)
                    }
                )
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}