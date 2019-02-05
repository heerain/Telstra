package com.example.hiren.telstraassignment.di

import com.example.hiren.telstraassignment.view.MainActivity
import dagger.Component

@CustomScope
@Component(modules = [ApiModule::class], dependencies = [NetworkComponent::class])
interface ApiComponent {
    fun inject(activity: MainActivity)
}
