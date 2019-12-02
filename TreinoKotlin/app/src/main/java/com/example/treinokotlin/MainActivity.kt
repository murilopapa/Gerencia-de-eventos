package com.example.treinokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.treinokotlin.data.dataSource.EventEntityDao
import com.example.treinokotlin.data.model.EventEntity
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
            val nameRecived = etName.text.toString()
            val dateRecived = etDate.text.toString()
            val localRecived = etLocal.text.toString()
            val descriptionRecived = etDescription.text.toString()

            entityEventDao.add(
                EventEntity(
                    name = nameRecived,
                    date = dateRecived,
                    local = localRecived,
                    description = descriptionRecived
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
