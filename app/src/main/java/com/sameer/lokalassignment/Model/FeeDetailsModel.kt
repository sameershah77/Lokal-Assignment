package com.sameer.lokalassignment

import com.google.gson.annotations.SerializedName


data class FeeDetailsModel(
    @SerializedName("V3")
    val feeFields: List<ContentModel>
)
