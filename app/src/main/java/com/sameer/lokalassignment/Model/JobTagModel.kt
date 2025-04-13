package com.sameer.lokalassignment

import com.google.gson.annotations.SerializedName

data class JobTagModel(
    @SerializedName("bg_color")
    val backgroundColor: String,

    @SerializedName("text_color")
    val textColor: String,

    @SerializedName("value")
    val tagValue: String
)
