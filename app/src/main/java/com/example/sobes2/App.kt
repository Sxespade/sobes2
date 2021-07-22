package com.example.sobes2

import android.app.Application
import com.example.sobes2.di.AppComponent
import com.example.sobes2.di.AppModule
import com.example.sobes2.di.DaggerAppComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class App : Application() {
    companion object {
        lateinit var instance: App
    }

    private var retrofit: Retrofit? = null

    lateinit var appComponent: AppComponent
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        initRetrofit()
    }


    private fun initRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofit(): Retrofit? {
        return retrofit
    }

}