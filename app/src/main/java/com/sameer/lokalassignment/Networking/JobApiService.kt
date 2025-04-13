package com.sameer.lokalassignment.Networking

import com.sameer.lokalassignment.JobResponse
import retrofit2.http.GET
import retrofit2.http.Query
interface JobApiService {
    @GET("jobs")
    suspend fun getJobs(
        @Query("page") page: Int
    ): JobResponse

}
