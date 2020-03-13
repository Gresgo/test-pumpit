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
import com.test.pumpit.R
import com.test.pumpit.adapters.MneNadoAdapter
import com.test.pumpit.application.App
import com.test.pumpit.databinding.FragmentYoudriveBinding

class YoudriveFragment : Fragment() {

    private lateinit var binding: FragmentYoudriveBinding
    private lateinit var youdriveViewModel: YoudriveViewModel
    private val carsAdapter = MneNadoAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        /**
         * bind view
         */
        youdriveViewModel = ViewModelProvider(this).get(YoudriveViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_youdrive, container, false)
        binding.viewModel = youdriveViewModel
        binding.executePendingBindings()

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

}