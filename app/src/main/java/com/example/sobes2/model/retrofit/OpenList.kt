package com.example.sobes2.model.retrofit

import com.example.sobes2.model.retrofit.entity.Example
import com.example.sobes2.model.retrofit.entity.Support
import retrofit2.Call
import retrofit2.http.GET

interface OpenList {
    @GET("api/users")
    fun loadList(): Call<Example>
}