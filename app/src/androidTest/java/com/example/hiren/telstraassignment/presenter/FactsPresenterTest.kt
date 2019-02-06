package com.example.hiren.telstraassignment.presenter

import com.example.hiren.telstraassignment.model.FactsResponseMain
import com.example.hiren.telstraassignment.service.FactsViewInterface
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito

class FactsPresenterTest {

    private lateinit var mPresenter : FactsPresenter

    @Mock
    lateinit var mFactsViewInterface : FactsViewInterface

    @Before
    fun setUp() {
        mPresenter = FactsPresenter(mFactsViewInterface)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun onCompleted() {
        mPresenter.onCompleted()
        Mockito.verify(mFactsViewInterface).onCompleted()
    }

    @Test
    fun onError(e: Throwable?) {
        mPresenter.onError(e)
        Mockito.verify(mFactsViewInterface).onError(e.toString())

    }

    @Test
    fun onNext(factsResponseMain: FactsResponseMain?) {
        mPresenter.onNext(factsResponseMain)
        Mockito.verify(mFactsViewInterface).onFacts(factsResponseMain!!)
    }
}