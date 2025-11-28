package com.yogi.portfolio.portfolio.Fragment

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.yogi.portfolio.R
import com.yogi.portfolio.databinding.FragmentLocationTrackBinding
import com.yogi.portfolio.portfolio.Helper.LocationHelper

class LocationTrackFragment : Fragment() {

    private lateinit var locationHelper: LocationHelper
    private lateinit var txtLocation: TextView
    private lateinit var binding: FragmentLocationTrackBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentLocationTrackBinding.inflate(inflater, container, false)

        locationHelper = LocationHelper()
        return binding.root
    }


}