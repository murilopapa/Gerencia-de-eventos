package com.example.treinokotlin.presentation.list.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.treinokotlin.R
import com.example.treinokotlin.presentation.list.presenter.EventListPresenter
import com.example.treinokotlin.presentation.list.view.adapter.EventListAdapter
import kotlinx.android.synthetic.main.activity_list.*
import org.koin.android.ext.android.inject

class ListActivity : AppCompatActivity() {

    private val presenter: EventListPresenter by inject()
    private val eventListAdapter = EventListAdapter()

    override fun onCreate(savedInstantState: Bundle?) {
        super.onCreate(savedInstantState)
        setContentView(R.layout.activity_list)
        presenter.getEvents()
        observeChanges()
        setupListeners()
        setupRecyclerView()
    }

    private fun observeChanges() {
        presenter.eventsLiveData.observe(this, Observer {
            pbItens.visibility = View.GONE
            eventListAdapter.setData(it)
        })
    }

    private fun setupListeners() {
        btnClear.setOnClickListener {
            presenter.removeAllEvents()
            eventListAdapter.setData(emptyList())
        }
    }

    private fun setupRecyclerView() {
        rvListEvent.apply {
            adapter = eventListAdapter
            layoutManager = LinearLayoutManager(this@ListActivity)
        }
    }
}