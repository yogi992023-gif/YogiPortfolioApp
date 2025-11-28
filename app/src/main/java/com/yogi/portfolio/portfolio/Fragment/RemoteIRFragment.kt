package com.yogi.portfolio.portfolio.Fragment

import android.app.Activity
import android.content.Context
import android.hardware.ConsumerIrManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yogi.portfolio.databinding.FragmentRemoteIRBinding

class RemoteIRFragment : Fragment() {

    lateinit var binding : FragmentRemoteIRBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding =  FragmentRemoteIRBinding.inflate(inflater, container, false)

        val irManager = context?.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager
        if (irManager.hasIrEmitter()) {
            Toast.makeText(activity, "IR supported ✅", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "No IR blaster ❌", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }


}