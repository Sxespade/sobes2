package com.example.sobes2.di

import com.example.sobes2.MainActivity
import com.example.sobes2.model.ListOfUsersModel
import com.example.sobes2.presenter.AboutTaskPresenter
import com.example.sobes2.presenter.AboutUserPresenter
import com.example.sobes2.presenter.ListOfUsersPresenter
import com.example.sobes2.presenter.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
        ListOfUsersModel::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(listOfUsersPresenter: ListOfUsersPresenter)
    fun inject(aboutTaskPresenter: AboutTaskPresenter)
    fun inject(listOfUsersModel: ListOfUsersModel)
    fun inject(aboutUserPresenter: AboutUserPresenter)
    fun inject(mainPresenter: MainPresenter)
}