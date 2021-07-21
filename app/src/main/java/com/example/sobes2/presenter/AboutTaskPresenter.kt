package com.example.sobes2.presenter

import com.example.sobes2.view.interfaces.MainView2
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AboutTaskPresenter: MvpPresenter<MainView2>() {

    @Inject
    lateinit var router: Router

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}