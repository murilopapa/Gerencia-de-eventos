package com.example.treinokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.treinokotlin.data.local.dataSource.EventEntityDao
import com.example.treinokotlin.data.local.model.EventEntity
import com.example.treinokotlin.dependencies.applicationModules
import com.example.treinokotlin.presentation.list.view.ListActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin

class MainActivity : AppCompatActivity() {

    private val entityEventDao: EventEntityDao by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startKoin(this, applicationModules)
        setupListeners()
    }

    private fun setupListeners() {
        btnConfirm.setOnClickListener {

            entityEventDao.add(
                EventEntity(
                    name = etName.text.toString(),
                    date = etDate.text.toString(),
                    local = etLocal.text.toString(),
                    description = etDescription.text.toString()
                )
            )

            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
        btnNext.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }
}
