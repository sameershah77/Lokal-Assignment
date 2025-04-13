package com.sameer.lokalassignment.Repository

import androidx.lifecycle.LiveData
import com.sameer.lokalassignment.Model.JobEntity
import com.sameer.lokalassignment.Utils.JobsDao

class JobSavedRepository(private val jobDao: JobsDao) {

    val allBookmarkedJobs: LiveData<List<JobEntity>> = jobDao.getAllJobs()

    suspend fun insert(job: JobEntity) {
        jobDao.insertJob(job)
    }

    suspend fun delete(job: JobEntity) {
        jobDao.deleteJob(job)
    }

    suspend fun isBookmarked(jobId: Int): Boolean {
        return jobDao.isBookmarked(jobId)
    }
}
