package com.test.pumpit.ui.issues

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.test.pumpit.R
import com.test.pumpit.databinding.FragmentSelectedIssueBinding

class SelectedIssueActivity : AppCompatActivity() {

    private lateinit var binding: FragmentSelectedIssueBinding
    private lateinit var viewModel: IssuesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO: use viewModelFactory to share vm between two activities or use fragment instead
        viewModel = ViewModelProvider(this).get(IssuesViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_selected_issue)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.issueBody.movementMethod = ScrollingMovementMethod()

        val issueId = intent.getIntExtra("issueId", 0)

        viewModel.loadCurrentIssue(issueId)
    }

}