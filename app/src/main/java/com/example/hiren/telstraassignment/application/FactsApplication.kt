package com.example.hiren.telstraassignment.application

import android.app.Application
import com.example.hiren.telstraassignment.di.*
import com.example.hiren.telstraassignment.model.ModelConstants

class FactsApplication : Application() {

    lateinit var apiComponent: ApiComponent

    private val networkComponent: NetworkComponent
        get() = DaggerNetworkComponent.builder()
            .networkModule(NetworkModule(ModelConstants.BASE_URL))
            .build()

    override fun onCreate() {
        super.onCreate()
        apiComponent = DaggerApiComponent.builder()
            .networkComponent(networkComponent)
            .build()
    }

}
