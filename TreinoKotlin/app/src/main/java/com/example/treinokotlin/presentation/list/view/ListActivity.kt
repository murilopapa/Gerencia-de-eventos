package com.example.treinokotlin.presentation.list.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.treinokotlin.R
import com.example.treinokotlin.mechanism.livedata.Status
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
            when (it?.status) {
                Status.SUCCESS -> {
                    pbItens.visibility = View.GONE
                    Toast.makeText(this, "Data success", Toast.LENGTH_SHORT).show()
                    it.data?.let { eventList ->
                        eventListAdapter.setData(eventList)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(this, "Data error", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    pbItens.visibility = View.VISIBLE
                    Toast.makeText(this, "Data loading", Toast.LENGTH_SHORT).show()
                }
            }

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