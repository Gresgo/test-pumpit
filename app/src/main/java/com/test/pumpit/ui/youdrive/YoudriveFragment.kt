package com.test.pumpit.ui.youdrive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.test.pumpit.R
import com.test.pumpit.adapters.MneNadoAdapter
import com.test.pumpit.application.App
import com.test.pumpit.databinding.FragmentYoudriveBinding

class YoudriveFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentYoudriveBinding
    private lateinit var youdriveViewModel: YoudriveViewModel
    private val carsAdapter = MneNadoAdapter(arrayListOf())
    private lateinit var gMap:GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        /**
         * bind view
         */
        youdriveViewModel = ViewModelProvider(activity!!).get(YoudriveViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_youdrive, container, false)
        binding.viewModel = youdriveViewModel
        binding.executePendingBindings()

        /**
         * add google map
         */
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        /**
         * init recyclerview (will be removed)
         */
        binding.carsRecycler.layoutManager = LinearLayoutManager(App().getContext())
        binding.carsRecycler.adapter = carsAdapter

        /**
         * observe carsList for recycler (wbr)
         */
        youdriveViewModel.carsList.observe(viewLifecycleOwner, Observer {
            it?.let { carsAdapter.replaceData(it) }
        })

        return binding.root
    }

    /**
     * move map camera when mapReady
     */
    override fun onMapReady(map: GoogleMap) {
        gMap = map

        val ekb = LatLng(56.8519, 60.6122)
        gMap.moveCamera(CameraUpdateFactory.newLatLng(ekb))
    }

}