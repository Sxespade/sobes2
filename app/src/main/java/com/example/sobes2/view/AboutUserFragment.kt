package com.example.sobes2.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.sobes2.App
import com.example.sobes2.R
import com.example.sobes2.databinding.FragmentAboutUserBinding
import com.example.sobes2.databinding.FragmentListOfUsersBinding
import com.example.sobes2.model.retrofit.entity.Datum
import com.example.sobes2.presenter.AboutUserPresenter
import com.example.sobes2.view.interfaces.BackButtonListener
import com.example.sobes2.view.interfaces.MainView3
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.presenter.ProvidePresenter

class AboutUserFragment : MvpAppCompatFragment(), MainView3, BackButtonListener {

    val DATUM_ARG = "datum"
    lateinit var binding: FragmentAboutUserBinding

    private val presenter: AboutUserPresenter by moxyPresenter {
        AboutUserPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    companion object {
        fun getInstance(datum: Datum?) = AboutUserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(DATUM_ARG, datum)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutUserBinding.bind(view)

        val datum = arguments?.getParcelable(DATUM_ARG) as Datum?
        binding.ivFirstName.text = datum?.firstName
        binding.ivLastName.text = datum?.lastName
        binding.ivEmail.text = datum?.email

        Glide.with(requireContext()).load(datum?.avatar).into(binding.ivAvatar)
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroy() {
        super.onDestroy()
        System.out.println("onDestroy")
    }

}