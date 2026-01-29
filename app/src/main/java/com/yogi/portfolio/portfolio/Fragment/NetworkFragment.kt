package com.yogi.portfolio.portfolio.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yogi.portfolio.R
import com.yogi.portfolio.databinding.FragmentDashboardBinding
import com.yogi.portfolio.databinding.FragmentNetworkBinding


class NetworkFragment : Fragment(), View.OnClickListener{

    private var _binding: FragmentNetworkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentNetworkBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(),"Welcome...", Toast.LENGTH_SHORT).show()
        binding.btnSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_submit -> {
                if(validation()){

                }
            }
        }
    }

    private fun validation(): Boolean {
        if(binding.etInput.text.toString().isEmpty()){
            Toast.makeText(requireContext(),"Please enter minutes", Toast.LENGTH_SHORT).show()
            return false
        }else if(binding.etInput.text.toString().toInt() >= 61){
            Toast.makeText(requireContext(),"Minutes between 1 to 60", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null    // ❗ prevent memory leak
    }
}