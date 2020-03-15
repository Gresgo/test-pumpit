package com.test.pumpit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.pumpit.databinding.ItemIssuesBinding
import com.test.pumpit.models.IssueModel

class IssuesAdapter(private var issuesList: ArrayList<IssueModel>,
                    private val onItemClick: OnIssueClickListener) : RecyclerView.Adapter<IssuesAdapter.IssuesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemIssuesBinding.inflate(inflater, parent, false)

        return IssuesHolder(binding)
    }

    override fun onBindViewHolder(holder: IssuesHolder, position: Int) {
        val issue = issuesList[position]
        holder.bind(issue)

        holder.itemView.setOnClickListener{
            onItemClick.onIssueClick(issue.number) //give current issue number to fragment
        }
    }

    override fun getItemCount(): Int = issuesList.size

    /**
     * update list in observable
     */
    fun replaceData(newData: ArrayList<IssueModel>) {
        issuesList = newData
        notifyDataSetChanged()
    }

    class IssuesHolder(private val binding: ItemIssuesBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * binding data from model to layout item
         */
        fun bind(issue: IssueModel) {
            binding.issueItem = issue
            binding.executePendingBindings()
        }

    }

    /**
     * item click handler
     */
    interface OnIssueClickListener {
        fun onIssueClick(id: Int)
    }
}