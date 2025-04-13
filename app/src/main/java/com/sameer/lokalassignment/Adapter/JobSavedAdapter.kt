package com.sameer.lokalassignment.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sameer.lokalassignment.Model.JobCategory
import com.sameer.lokalassignment.Model.JobEntity
import com.sameer.lokalassignment.Transition.OnJobClickListener
import com.sameer.lokalassignment.databinding.ItemJobBinding
import com.sameer.lokalassignment.databinding.ItemSavedJobsBinding

class JobSavedAdapter() : ListAdapter<JobEntity, JobSavedAdapter.JobViewHolder>(diffCallback) {

    inner class JobViewHolder(val binding: ItemSavedJobsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(job: JobEntity) = with(binding) {
            val categoryEnum = JobCategory.fromId(job.categoryId)
            jobTitle.text = categoryEnum.title
            companyName.text = job.companyName
            location.text = job.location.replaceFirstChar { it.uppercaseChar() }
            maxSalary.text = job.maxSalary.toString()
            minSalary.text = job.minSalary.toString()
            experience.text = job.experience.ifBlank { "Not specified" }
            openingsCount.text = job.openingCount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemSavedJobsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<JobEntity>() {
            override fun areItemsTheSame(oldItem: JobEntity, newItem: JobEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: JobEntity, newItem: JobEntity): Boolean =
                oldItem == newItem
        }
    }
}