package com.example.sobes2.presenter

import android.util.Log
import com.example.sobes2.model.retrofit.entity.Datum
import com.example.sobes2.view.interfaces.MainView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers.io
import moxy.MvpPresenter
import retrofit2.Retrofit
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ListOfUsersPresenter : MvpPresenter<MainView>() {


    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var router: Router

//    @Inject
//    lateinit var database: Database

    @Inject
    lateinit var listOfDatum: Observable <List<Datum>>




    fun getUserList() {
        listOfDatum.subscribeOn(io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            Log.d("TAG1", "getUserList: $it")

//            database.userDao()?.insert(it[0])
//
//            Log.d("TAG2", "getUserList: "+            database.userDao()?.getAll())
//            database.userDao()?.getAll()

            if (it.isEmpty()) {
               viewState.showDialog()
            }
            viewState.setAdapter(it)
            viewState.setProgressBar()
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}