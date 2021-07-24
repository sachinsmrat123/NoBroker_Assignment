package com.example.nobroker_assignment.data.models


import com.google.gson.annotations.SerializedName

data class ResponseModelItem(
    @SerializedName("image")
    val image: String,
    @SerializedName("subTitle")
    val subTitle: String,
    @SerializedName("title")
    val title: String
)