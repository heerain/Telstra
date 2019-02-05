package com.example.hiren.telstraassignment.base


import rx.Observable
import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

abstract class PresenterImpl : Presenter{

    private var mCompositeSubscription : CompositeSubscription ?= null

    override fun onCreate() {
    }

    override fun onPause() {
    }

    override fun onResume() {
        configureSubscription()
    }

    private fun configureSubscription() : CompositeSubscription {
        if(mCompositeSubscription == null || mCompositeSubscription?.isUnsubscribed()!!){
                mCompositeSubscription = CompositeSubscription()
        }
        return mCompositeSubscription as CompositeSubscription
    }


    override fun onDestroy() {
        unSubscribeAll()
    }

    protected fun unSubscribeAll() {
        if(mCompositeSubscription != null){
            mCompositeSubscription?.unsubscribe()
            mCompositeSubscription?.clear()
        }
    }

    protected fun <F> subscribe(observable: Observable<F>, observer: Observer<F>){
        var subscriptions : Subscription = observable
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(Schedulers.computation())
            .subscribe(observer)
        configureSubscription().add(subscriptions)

    }
}