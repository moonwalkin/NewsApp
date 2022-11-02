package com.example.newsapp

import android.app.Application
import android.media.Image
import com.example.newsapp.di.DaggerApplicationComponent

class App : Application() {

    val component = DaggerApplicationComponent.factory().create(this)

}