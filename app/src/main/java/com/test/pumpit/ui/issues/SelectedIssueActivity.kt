package com.test.pumpit.ui.issues

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.pumpit.R
import com.test.pumpit.adapters.LabelsAdapter
import com.test.pumpit.application.App
import com.test.pumpit.databinding.FragmentSelectedIssueBinding

class SelectedIssueActivity : AppCompatActivity() {

    private lateinit var binding: FragmentSelectedIssueBinding
    private lateinit var viewModel: IssuesViewModel
    private val labelsAdapter = LabelsAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar!!.title = "Selected Issue"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //TODO: use viewModelFactory to share vm between two activities or use fragment instead
        viewModel = ViewModelProvider(this).get(IssuesViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_selected_issue)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.issueBody.movementMethod = ScrollingMovementMethod()

        binding.issueLabelsRecycler.layoutManager = LinearLayoutManager(App().getContext())
        binding.issueLabelsRecycler.adapter = labelsAdapter

        //TODO: make this better
        viewModel.labelsList.observe(this, Observer {
            it?.let { labelsAdapter.replaceData(it) }
        })

        val issueId = intent.getIntExtra("issueId", 0)

        if (savedInstanceState == null) {
            viewModel.loadCurrentIssue(issueId)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}