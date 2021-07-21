package com.example.sobes2

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.sobes2.navigation.Screens
import com.example.sobes2.presenter.MainPresenter
import com.example.sobes2.view.interfaces.BackButtonListener
import com.example.sobes2.view.interfaces.MainViewMain
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers.io
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import java.util.*
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainViewMain {


    val presenter: MainPresenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val navigator: Navigator =
        SupportAppNavigator(this, supportFragmentManager, R.id.container)

    lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.instance.appComponent.inject(this)

        bottomNavigationView =
            findViewById<View>(R.id.bottom_navigation_bar) as BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.list_users -> {
                    router.replaceScreen(Screens.ListOfUsers())
                    return@setOnItemSelectedListener true
                }
                R.id.action_task -> {
                    router.replaceScreen(Screens.AboutTask())
                    return@setOnItemSelectedListener true
                }
            }
            false
        }


    }

    override fun onStart() {
        super.onStart()
        router.replaceScreen(Screens.ListOfUsers())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }

}