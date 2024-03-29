package com.example.sobes2.view.interfaces

import com.example.sobes2.model.retrofit.entity.Datum
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainViewMain : MvpView {
}
