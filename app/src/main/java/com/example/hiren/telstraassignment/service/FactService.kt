package com.example.hiren.telstraassignment.service

import com.example.hiren.telstraassignment.model.ModelConstants.Companion.ENDPOINT_URL
import com.example.hiren.telstraassignment.model.FactsResponseMain
import retrofit2.http.GET
import rx.Observable

interface FactService {

    @get:GET (ENDPOINT_URL)
     val facts: Observable<FactsResponseMain>
}
