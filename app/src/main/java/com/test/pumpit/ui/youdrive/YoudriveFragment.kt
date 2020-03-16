package com.test.pumpit.ui.youdrive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.test.pumpit.R
import com.test.pumpit.databinding.FragmentYoudriveBinding
import com.test.pumpit.models.CarModel


class YoudriveFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentYoudriveBinding
    private lateinit var youdriveViewModel: YoudriveViewModel
    private var gMap: GoogleMap ?= null
    private var carsList = ArrayList<CarModel>()

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
         * observe carsList for recycler (wbr)
         */
        youdriveViewModel.carsList.observe(viewLifecycleOwner, Observer {
            it?.let {
                carsList = it
                if (gMap != null) {
                    showMarkers(carsList)
                }
            }
        })

        return binding.root
    }

    /**
     * move map camera when mapReady
     */
    override fun onMapReady(map: GoogleMap?) {
        gMap = map

        val ekb = LatLng(56.8519, 60.6122)
        gMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(ekb, 11F))

        showMarkers(carsList)
    }

    /**
     * show markers on map
     */
    private fun showMarkers(list: ArrayList<CarModel>) {
        gMap!!.clear()
        val markers = arrayOfNulls<MarkerOptions>(list.size)
        for (i in 0 until list.size) {
            markers[i] = MarkerOptions().position(LatLng(list[i].latitude, list[i].longitude)).title(list[i].model)
            gMap!!.addMarker(markers[i])
        }
    }

}