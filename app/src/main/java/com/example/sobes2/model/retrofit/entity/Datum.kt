package com.example.sobes2.model.retrofit.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import dagger.Provides
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Datum(
    @SerializedName("id")
    @Expose
    var id: String = "null",

    @SerializedName("email")
    @Expose
    var email: String? = null,

    @SerializedName("first_name")
    @Expose
    var firstName: String? = null,

    @SerializedName("last_name")
    @Expose
    var lastName: String? = null,

    @SerializedName("avatar")
    @Expose
    var avatar: String? = null
) : Parcelable