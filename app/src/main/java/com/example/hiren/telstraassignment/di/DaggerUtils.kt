package com.example.hiren.telstraassignment.di

import android.support.v7.app.AppCompatActivity
import com.example.hiren.telstraassignment.application.FactsApplication


/*
 apiComponent is the extention property of the AppCompact Activity
 so that where ever any class extends AppCompactActivity you can inject the
 dependency like  below and no need to write everywhere
 */
val AppCompatActivity.apiComponent : ApiComponent
    get() = (application as FactsApplication).apiComponent

//This is the extention function for the samw code written below
/*
 fun AppCompatActivity.component() : ApiComponent = (application as FactsApplication).apiComponent
 */