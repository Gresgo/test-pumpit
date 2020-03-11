package com.test.pumpit.ui.issues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.pumpit.R
import com.test.pumpit.databinding.FragmentIssuesBinding

class IssuesFragment : Fragment() {

    private lateinit var binding: FragmentIssuesBinding
    private lateinit var issuesViewModel: IssuesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        issuesViewModel = ViewModelProvider(this).get(IssuesViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issues, container, false)
        binding.viewModel = issuesViewModel
        binding.executePendingBindings()

        return binding.root
    }

}