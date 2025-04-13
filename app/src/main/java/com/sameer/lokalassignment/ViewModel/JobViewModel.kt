package com.sameer.lokalassignment.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sameer.lokalassignment.Repository.JobRepository

class JobViewModel : ViewModel() {
    private val repository = JobRepository()
    val jobList = repository.getJobs().cachedIn(viewModelScope)
}