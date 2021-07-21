package com.example.sobes2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sobes2.App
import com.example.sobes2.R
import com.example.sobes2.presenter.AboutTaskPresenter
import com.example.sobes2.view.interfaces.BackButtonListener
import com.example.sobes2.view.interfaces.MainView2
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class AboutTaskFragment: MvpAppCompatFragment(), MainView2, BackButtonListener {

    private val presenter: AboutTaskPresenter by moxyPresenter {
        AboutTaskPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    companion object {
        fun getInstance(): AboutTaskFragment {
            return AboutTaskFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task,container,false)
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroy() {
        super.onDestroy()
        System.out.println("onDestroy")
    }

}