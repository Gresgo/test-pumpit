package com.test.pumpit.ui.issues

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

class IssuesFragment : Fragment(), IssuesAdapter.OnIssueClickListener {

    private lateinit var binding: FragmentIssuesBinding
    private lateinit var issuesViewModel: IssuesViewModel
    private val issuesAdapter = IssuesAdapter(arrayListOf(), this)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        /**
         * bind view
         */
        issuesViewModel = ViewModelProvider(activity!!).get(IssuesViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issues, container, false)
        binding.viewModel = issuesViewModel
        binding.executePendingBindings()
        issuesViewModel.loadIssues()
        /**
         * init recyclerview
         */
        binding.issuesRecycler.layoutManager = LinearLayoutManager(App().getContext())
        binding.issuesRecycler.adapter = issuesAdapter

        /**
         * observe list for updates
         */
        issuesViewModel.issuesList.observe(viewLifecycleOwner, Observer {
            it?.let { issuesAdapter.replaceData(ArrayList(it)) }
        })

        return binding.root
    }

    /**
     * open issue with current id
     */
    override fun onIssueClick(id: Int) {
        val intent = Intent(activity, SelectedIssueActivity::class.java)
        intent.putExtra("issueId", id)
        startActivity(intent)
    }

}