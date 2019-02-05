package com.example.hiren.telstraassignment.view


import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
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
    lateinit var mService: FactService

    private lateinit var mPresenter: FactsPresenter
    private lateinit var mFactsRecyclerView: RecyclerView
    private lateinit var mFactsAdaper: FactsAdaper
    private lateinit var mDialog: ProgressDialog
    private lateinit var title: String
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiComponent.inject(this)

        mPresenter = FactsPresenter(this)
        mPresenter.onCreate()

        initView()
        configView()

    }

    private fun setToolbarTitle() {
        supportActionBar?.title = title
    }

    private fun initView() {
        mFactsRecyclerView = findViewById(R.id.recyclerview_facts)
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
    }

    private fun configView() {
        mFactsRecyclerView.layoutManager = LinearLayoutManager(this)
        mFactsRecyclerView.setHasFixedSize(true)
        mFactsRecyclerView.setItemViewCacheSize(20)
        mFactsAdaper = FactsAdaper(LayoutInflater.from(this))
        mFactsRecyclerView.adapter = mFactsAdaper
        mSwipeRefreshLayout.setOnRefreshListener {
            mPresenter.fetchFacts()
            mDialog = ProgressDialog.show(this, getString(R.string.please_wait), getString(R.string.download_msg), true)
        }
    }

    override fun onResume() {
        super.onResume()
        mPresenter.onResume()
        mPresenter.fetchFacts()
        mDialog = ProgressDialog.show(this, getString(R.string.please_wait), getString(R.string.download_msg), true)
    }

    override fun onCompleted() {
        mDialog.dismiss()
    }

    override fun onError(message: String?) {
        mDialog.dismiss()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFacts(factsResponseMain: FactsResponseMain) {
        title = factsResponseMain.title
        mFactsAdaper.addFactsList(factsResponseMain.rows)
        setToolbarTitle()
        if (mSwipeRefreshLayout.isRefreshing) {
            mSwipeRefreshLayout.isRefreshing = false
        }

    }

    override fun getFacts(): Observable<FactsResponseMain> {
        return mService.facts
    }
}
