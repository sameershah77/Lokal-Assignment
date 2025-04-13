package com.sameer.lokalassignment.Utils

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sameer.lokalassignment.Model.JobEntity

@Dao
interface JobsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: JobEntity)

    @Delete
    suspend fun deleteJob(job: JobEntity)

    @Query("SELECT * FROM JobEntity")
    fun getAllJobs(): LiveData<List<JobEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM JobEntity WHERE id = :jobId)")
    suspend fun isBookmarked(jobId: Int): Boolean
}
