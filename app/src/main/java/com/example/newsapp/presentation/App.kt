package com.example.newsapp.presentation

import android.app.Application
import com.example.newsapp.di.DaggerApplicationComponent

class App : Application() {

    val component = DaggerApplicationComponent.factory().create(this)

}