package com.example.sobes2.navigation

import com.example.sobes2.model.retrofit.entity.Datum
import com.example.sobes2.view.AboutTaskFragment
import com.example.sobes2.view.AboutUserFragment
import com.example.sobes2.view.ListOfUsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class ListOfUsers : SupportAppScreen() {
        override fun getFragment() = ListOfUsersFragment.getInstance()
    }
    class AboutTask : SupportAppScreen() {
        override fun getFragment() = AboutTaskFragment.getInstance()
    }

    class AboutUser(val datum: Datum?) : SupportAppScreen() {
        override fun getFragment(): AboutUserFragment {
            return AboutUserFragment.getInstance(datum)
        }
    }
}
