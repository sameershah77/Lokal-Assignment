package com.sameer.lokalassignment.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.sameer.lokalassignment.databinding.FragmentJobBinding
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.sameer.lokalassignment.Adapter.JobAdapter
import com.sameer.lokalassignment.Model.JobEntity
import com.sameer.lokalassignment.R
import com.sameer.lokalassignment.Transition.OnJobClickListener
import com.sameer.lokalassignment.ViewModel.JobViewModel

class JobFragment : Fragment(), OnJobClickListener {
    private lateinit var jobAdapter: JobAdapter
    private lateinit var jobViewModel: JobViewModel

    private lateinit var binding: FragmentJobBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentJobBinding.inflate(inflater, container, false)

        jobAdapter = JobAdapter(this)
        jobViewModel = ViewModelProvider(requireActivity())[JobViewModel::class.java]

        jobAdapter.addLoadStateListener { loadState ->

            val isLoading = loadState.source.refresh is LoadState.Loading
            val isError = loadState.source.refresh is LoadState.Error
            val isEmpty = loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    jobAdapter.itemCount == 0

            binding.progressBar.isVisible = isLoading
            binding.jobRecyclerView.isVisible = !isLoading && !isEmpty && !isError
            binding.emptyStateView.isVisible = isEmpty
            binding.errorStateView.isVisible = isError
        }

        binding.jobRecyclerView.adapter = jobAdapter
        binding.jobRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        jobViewModel.jobList.observe(requireActivity()) {
            jobAdapter.submitData(lifecycle, it)
        }

        return binding.root
    }

    override fun onShowMoreClicked(job: JobEntity) {
        val bundle = Bundle().apply {
            putParcelable("job", job)
        }
        val detailsFragment = JobDetailsFragment().apply {
            arguments = bundle
        }

        parentFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
            .replace(R.id.fragment_container, detailsFragment)
            .addToBackStack(null)
            .commit()
    }
}