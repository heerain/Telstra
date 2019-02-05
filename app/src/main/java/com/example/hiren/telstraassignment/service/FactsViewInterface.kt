package com.example.hiren.telstraassignment.service

import com.example.hiren.telstraassignment.model.FactsResponseMain
import rx.Observable

interface FactsViewInterface {
    fun onCompleted()
    fun onError(message: String?)
    fun onFacts(factsResponseMain: FactsResponseMain)
    fun getFacts() : Observable<FactsResponseMain>
}