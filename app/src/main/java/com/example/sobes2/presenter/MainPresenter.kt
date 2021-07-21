package com.example.sobes2.presenter

import com.example.sobes2.navigation.Screens
import com.example.sobes2.view.interfaces.MainViewMain
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainViewMain>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.ListOfUsers())
    }

    fun backClicked() {
        router.exit()
    }
}

