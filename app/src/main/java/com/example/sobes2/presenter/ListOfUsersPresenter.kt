package com.example.sobes2.presenter

import android.content.Context
import android.util.Log
import com.example.sobes2.model.AndroidNetworkStatus
import com.example.sobes2.model.retrofit.entity.Datum
import com.example.sobes2.model.room.Database
import com.example.sobes2.view.interfaces.MainView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers.io
import moxy.MvpPresenter
import retrofit2.Retrofit
import ru.geekbrains.poplib.mvp.model.entity.room.RoomUser
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class ListOfUsersPresenter : MvpPresenter<MainView>() {


    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var database: Database

    @Inject
    lateinit var listOfDatum: Observable<List<Datum>>


    fun getUserList() =
        listOfDatum.flatMap { list ->
            if (list.isEmpty()) {
                viewState.showDialog()
            }
            viewState.setAdapter(list)
            viewState.setProgressBar()


            Observable.create<String> {
                var n = 0
                for (s in list) {
                    val roomUser = RoomUser(
                        n.toString(), s.firstName.toString(),
                        s.email.toString(),s.lastName.toString(), s.avatar.toString()
                    )
                    database.userDao.insert(roomUser)
                    n++
                }
                Log.d("TAG5", "getUserList: " + database.userDao.getAll())
            }.subscribeOn(io()).observeOn(AndroidSchedulers.mainThread()).subscribe { }

            Observable.just(list)
        }.subscribeOn(io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            viewState.initDatabase()
        }


    fun getDataFromDatabase(context: Context) {
        val androidNetworkStatus = AndroidNetworkStatus(context)
        androidNetworkStatus.isOnlineSingle().subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { boolean ->
                Log.d("TAG6", "getDataFromDatabase: $boolean")
                if (boolean == false) {
                    Observable.create<List<Datum>> {
                        Log.d("TAG6", "getDataFromDatabase: ${database.userDao.getAll()}")
                        val list = database.userDao.getAll()
                        val listOfDatum = mutableListOf<Datum>()
                        for (s in list) {
                            val datum = Datum(s.id,s.email, s.firstName,s.lastName, s.img)
                            listOfDatum.add(datum)
                        }
                        it.onNext(listOfDatum)
                        Log.d("TAG6", "getDataFromDatabase: $listOfDatum")
                    }.subscribeOn(io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            viewState.setAdapter(it)
                        }
                }
            }
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }


}