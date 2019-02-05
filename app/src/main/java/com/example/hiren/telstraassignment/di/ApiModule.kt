package com.example.hiren.telstraassignment.di

import com.example.hiren.telstraassignment.service.FactService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    @CustomScope
    internal fun providesFactsService(retrofit: Retrofit): FactService {
        return retrofit.create(FactService::class.java)
    }
}
