package com.test.pumpit.ui.issues

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
import com.test.pumpit.adapters.IssuesAdapter
import com.test.pumpit.application.App
import com.test.pumpit.databinding.FragmentIssuesBinding

class IssuesFragment : Fragment() {

    private lateinit var binding: FragmentIssuesBinding
    private lateinit var issuesViewModel: IssuesViewModel
    private val issuesAdapter = IssuesAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        /**
         * bind view
         */
        issuesViewModel = ViewModelProvider(this).get(IssuesViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issues, container, false)
        binding.viewModel = issuesViewModel
        binding.executePendingBindings()

        /**
         * init recyclerview
         */
        binding.issuesRecycler.layoutManager = LinearLayoutManager(App().getContext())
        binding.issuesRecycler.adapter = issuesAdapter

        /**
         * observe list for updates
         */
        issuesViewModel.issuesList.observe(viewLifecycleOwner, Observer {
            it?.let { issuesAdapter.replaceData(it) }
        })

        return binding.root
    }

}