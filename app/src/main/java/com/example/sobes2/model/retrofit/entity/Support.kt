package com.example.sobes2.model.retrofit.entity

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Support (
    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("text")
    @Expose
    var text: String? = null
)