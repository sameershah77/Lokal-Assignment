package com.sameer.lokalassignment.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sameer.lokalassignment.Adapter.JobSavedAdapter
import com.sameer.lokalassignment.Model.JobEntity
import com.sameer.lokalassignment.R
import com.sameer.lokalassignment.ViewModel.JobsSavedViewModel
import com.sameer.lokalassignment.databinding.FragmentBookmarkBinding
import kotlinx.coroutines.launch

class BookmarkFragment : Fragment() {
    private lateinit var jobSavedViewModel: JobsSavedViewModel
    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var list: MutableList<JobEntity>
    private lateinit var adapter : JobSavedAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        jobSavedViewModel = ViewModelProvider(requireActivity())[JobsSavedViewModel::class.java]

        list = mutableListOf()

        adapter = JobSavedAdapter()
        binding.jobRecyclerView.adapter = adapter
        binding.jobRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        lifecycleScope.launch {
            jobSavedViewModel.allJobs.observe(viewLifecycleOwner) { jobs->

                Log.d("lidwbdjhb ", "iuedwgoiubd")
                list.clear()
                list.addAll(jobs)
                adapter.submitList(jobs)
            }
        }




        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val job = adapter.currentList[position]
                jobSavedViewModel.delete(job)  // Call your ViewModel to delete from Room DB
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.jobRecyclerView)

        return binding.root
    }
}