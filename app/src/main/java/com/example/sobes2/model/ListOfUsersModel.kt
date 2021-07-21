package com.example.sobes2.model

import android.util.Log
import com.example.sobes2.model.retrofit.OpenList
import com.example.sobes2.model.retrofit.entity.Datum
import com.example.sobes2.model.retrofit.entity.Example
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

@Module
class ListOfUsersModel {

    @Provides
    fun getUserList(retrofit: Retrofit) = Observable.create <List<Datum>> {
        var listOfDatum: MutableList<Datum>
        retrofit.create(OpenList::class.java).loadList().enqueue(object: Callback<Example>{
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                Log.d("TAG1", "onResponse: " + response.body()?.data)
                listOfDatum = response.body()?.data as MutableList<Datum>
                it.onNext(listOfDatum)
            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.d("TAG1", "onFailure: $t")
                it.onNext(emptyList())
            }
        })
    }

}