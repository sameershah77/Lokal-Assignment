package com.sameer.lokalassignment.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sameer.lokalassignment.DB.JobDatabase
import com.sameer.lokalassignment.Model.JobEntity
import com.sameer.lokalassignment.Repository.JobSavedRepository
import kotlinx.coroutines.launch

class JobsSavedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JobSavedRepository

    val allJobs: LiveData<List<JobEntity>>

    init {
        val jobDao = JobDatabase.getDatabase(application).jobDao()
        repository = JobSavedRepository(jobDao)
        allJobs = repository.allBookmarkedJobs
    }

    fun insert(job: JobEntity) = viewModelScope.launch {
        repository.insert(job)
    }

    fun delete(job: JobEntity) = viewModelScope.launch {
        repository.delete(job)
    }

    suspend fun isBookmarked(jobId: Int): Boolean {
        return repository.isBookmarked(jobId)
    }
}