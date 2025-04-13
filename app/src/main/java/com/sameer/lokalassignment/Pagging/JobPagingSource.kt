package com.sameer.lokalassignment.Pagging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sameer.lokalassignment.Networking.JobApiService
import com.sameer.lokalassignment.Model.JobEntity
import com.sameer.lokalassignment.Model.toJobs

class JobPagingSource(val jobService: JobApiService) : PagingSource<Int, JobEntity>() {
    override fun getRefreshKey(state: PagingState<Int, JobEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JobEntity> {
        return try {
            val page = params.key ?: 1
            val jobsDto = jobService.getJobs(2)
            val jobs = jobsDto.toJobs()

            val isEndReached = jobs.isEmpty() // <- actual fix here

            Log.d("PagingSource", "Page: $page, Fetched: ${jobs.size} items, EndReached: $isEndReached")

            LoadResult.Page(
                data = jobs,
                nextKey = if (isEndReached) null else page + 1,
                prevKey = if (page == 1) null else page - 1
            )

        } catch (e: Throwable) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }


}