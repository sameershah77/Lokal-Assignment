package com.sameer.lokalassignment
import com.google.gson.annotations.SerializedName

data class ContentModel(
    @SerializedName("V3")
    val v3Fields: List<ContentFieldModel>
)
