package com.example.daggertraining

import android.app.Application
import android.content.Context
import com.example.api.di.ApiDepsProvider
import com.example.api.di.ApiDepsStore
import com.example.daggertraining.di.AppComponent
import com.example.daggertraining.di.DaggerAppComponent

class MainApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .apiKey("9e2e51f8150a423399d699a64232550d")
            .build()

        ApiDepsStore.deps = appComponent
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else       -> this.applicationContext.appComponent
    }
