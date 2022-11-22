package com.example.var7individpraktik

import android.app.Application

class FilmApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        FilmRepository.initialize(this)
    }
}