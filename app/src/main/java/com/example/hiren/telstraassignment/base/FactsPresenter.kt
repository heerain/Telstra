package com.example.hiren.telstraassignment.base

import com.example.hiren.telstraassignment.model.FactsResponseMain
import com.example.hiren.telstraassignment.service.FactsViewInterface
import rx.Observer

class FactsPresenter : PresenterImpl, Observer<FactsResponseMain> {

    private lateinit var mFactsViewInterface: FactsViewInterface

    constructor(factsViewInterface: FactsViewInterface) : super(){
        mFactsViewInterface = factsViewInterface
    }

    override fun onCompleted() {
        mFactsViewInterface.onCompleted()
    }

    override fun onError(e: Throwable?) {
        mFactsViewInterface.onError(e?.message)
    }

    override fun onNext(factsResponseMain: FactsResponseMain?) {
        factsResponseMain?.let { mFactsViewInterface.onFacts(it) }
    }

    fun fetchFacts() {
        unSubscribeAll()
        subscribe(mFactsViewInterface.getFacts(),this)
    }

}