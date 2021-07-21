package com.example.sobes2.presenter

import com.example.sobes2.view.interfaces.MainView3
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AboutUserPresenter: MvpPresenter<MainView3>() {

    @Inject
    lateinit var router: Router

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}