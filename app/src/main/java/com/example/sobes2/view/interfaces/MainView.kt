package com.example.sobes2.view.interfaces

import android.content.Context
import android.graphics.Bitmap
import com.example.sobes2.model.retrofit.entity.Datum
import io.reactivex.rxjava3.core.Single
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun setAdapter(it: List<Datum>)
    fun setProgressBar()
    fun showDialog()
    fun initDatabase()
}
