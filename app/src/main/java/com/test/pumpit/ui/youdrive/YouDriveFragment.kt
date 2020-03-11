package com.test.pumpit.ui.youdrive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.pumpit.R
import com.test.pumpit.databinding.FragmentYoudriveBinding

class YouDriveFragment : Fragment() {

    private lateinit var binding: FragmentYoudriveBinding
    private lateinit var youDriveViewModel: YouDriveViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        youDriveViewModel = ViewModelProvider(this).get(YouDriveViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_youdrive, container, false)
        binding.viewModel = youDriveViewModel
        binding.executePendingBindings()

        return binding.root
    }

}