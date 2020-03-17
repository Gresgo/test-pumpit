package com.test.pumpit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.pumpit.databinding.ItemLabelBinding
import com.test.pumpit.models.LabelModel

class LabelsAdapter(private var labels: ArrayList<LabelModel>) : RecyclerView.Adapter<LabelsAdapter.LabelsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLabelBinding.inflate(inflater, parent, false)

        return LabelsHolder(binding)
    }

    override fun onBindViewHolder(holder: LabelsHolder, position: Int) {
        val label = labels[position]
        holder.bind(label)
    }

    override fun getItemCount(): Int = labels.size

    fun replaceData(newData: ArrayList<LabelModel>) {
        labels = newData
        notifyDataSetChanged()
    }

    class LabelsHolder(private val binding: ItemLabelBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(label: LabelModel) {
            binding.labelItem = label
            binding.executePendingBindings()
        }
    }
}