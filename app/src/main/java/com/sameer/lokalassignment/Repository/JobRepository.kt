package com.sameer.lokalassignment.Repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.sameer.lokalassignment.Pagging.JobPagingSource
import com.sameer.lokalassignment.Networking.RetrofitInstance

class JobRepository {
    fun getJobs() = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { JobPagingSource(RetrofitInstance.api) }
    ).liveData
}