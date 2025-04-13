package com.sameer.lokalassignment

import com.google.gson.annotations.SerializedName

data class JobResponse(
    @SerializedName("results")
    val jobResults: List<JobResultModel>
)
