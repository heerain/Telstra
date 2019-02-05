package com.example.hiren.telstraassignment.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@Module
class NetworkModule(private val mBaseUrl: String) {

    @Provides
    @Singleton
    internal fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Provides
    @Singleton
    internal fun provideRxJavaCallAdapterFactory(): RxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create()


    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(mBaseUrl)
            .addConverterFactory(provideGsonConverterFactory())
            .addCallAdapterFactory(provideRxJavaCallAdapterFactory())
            .build()
    }
}
