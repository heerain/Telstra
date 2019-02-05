package com.example.hiren.telstraassignment.view


import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.ListView
import android.widget.Toast
import com.example.hiren.telstraassignment.R
import com.example.hiren.telstraassignment.base.FactsPresenter
import com.example.hiren.telstraassignment.di.apiComponent
import com.example.hiren.telstraassignment.model.FactsAdaper
import com.example.hiren.telstraassignment.model.FactsResponseMain
import com.example.hiren.telstraassignment.service.FactService
import com.example.hiren.telstraassignment.service.FactsViewInterface
import rx.Observable
import javax.inject.Inject

class MainActivity : AppCompatActivity(), FactsViewInterface {

    @Inject
    lateinit var mService : FactService

    private lateinit var mPresenter : FactsPresenter
    private lateinit var mFactsRecyclerView: RecyclerView
    private lateinit var mFactsAdaper: FactsAdaper
    private lateinit var mDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiComponent.inject(this)

        mPresenter = FactsPresenter(this)
        mPresenter.onCreate()

        initView()
        configView()


    }

    private fun initView() {
        mFactsRecyclerView = findViewById(R.id.recyclerview_facts)

    }

    private fun configView() {
        mFactsRecyclerView.layoutManager = LinearLayoutManager(this)
        mFactsAdaper = FactsAdaper(LayoutInflater.from(this))
        mFactsRecyclerView.adapter = mFactsAdaper
    }

    override fun onResume() {
        super.onResume()
        mPresenter.onResume()
        mPresenter.fetchFacts()
        mDialog = ProgressDialog.show(this,"Downloading List. .","Please wait . . .",true)

    }

    override fun onCompleted() {
        mDialog.dismiss()
    }

    override fun onError(message: String?) {
        mDialog.dismiss()
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun onFacts(factsResponseMain: FactsResponseMain) {
        mFactsAdaper.addFactsList(factsResponseMain.rows)
    }

    override fun getFacts(): Observable<FactsResponseMain> {
        return mService.facts
    }

}
