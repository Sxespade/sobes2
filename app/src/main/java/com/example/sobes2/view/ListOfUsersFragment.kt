package com.example.sobes2.view

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.sobes2.App
import com.example.sobes2.MainActivity
import com.example.sobes2.R
import com.example.sobes2.databinding.FragmentListOfUsersBinding
import com.example.sobes2.model.AndroidNetworkStatus
import com.example.sobes2.model.adapters.ListUsersAdapter
import com.example.sobes2.model.adapters.MyOnClickListener
import com.example.sobes2.model.retrofit.entity.Datum
import com.example.sobes2.navigation.Screens
import com.example.sobes2.presenter.ListOfUsersPresenter
import com.example.sobes2.view.interfaces.BackButtonListener
import com.example.sobes2.view.interfaces.MainView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers.io
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ListOfUsersFragment : MvpAppCompatFragment(), MainView, BackButtonListener {

    private val presenter: ListOfUsersPresenter by moxyPresenter {
        ListOfUsersPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    lateinit var binding: FragmentListOfUsersBinding

    companion object {
        fun getInstance(): ListOfUsersFragment {
            return ListOfUsersFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListOfUsersBinding.bind(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_of_users, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.getUserList()
    }




    override fun setAdapter(it: List<Datum>) {
        binding.recycle.adapter = ListUsersAdapter(it, object : MyOnClickListener {
            override fun onClicked(datum: Datum) {
                presenter.router.navigateTo(Screens.AboutUser(datum))
            }
        })
    }

    override fun setProgressBar() {
        binding.progressBar2.visibility = View.GONE
    }

    override fun showDialog() {
        Toast.makeText(requireContext(), "Ошибка подключения", Toast.LENGTH_LONG).show()
    }

    override fun initDatabase() {
        presenter.getDataFromDatabase(requireContext())
    }


    override fun backPressed() = presenter.backPressed()

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }

}