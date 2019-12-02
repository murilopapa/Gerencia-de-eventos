package com.example.treinokotlin.dependencies

import androidx.room.Room
import com.example.treinokotlin.data.database.AppDataBase
import com.example.treinokotlin.data.repository.events.EventsRepository
import com.example.treinokotlin.domain.usecases.EventsUseCase
import com.example.treinokotlin.presentation.list.presenter.EventListPresenter
import org.koin.dsl.module.module


val databaseModules = module {

    single {
        Room.databaseBuilder(
            get(),
            AppDataBase::class.java,
            "app-database"
        )
            .allowMainThreadQueries().build()
    }

    single { get<AppDataBase>().eventEntityDao() }
}

val presenterModules = module {
    factory {
        EventListPresenter(get())
    }
}
val repositoryModules = module {
    single { EventsRepository(get()) }
}
val useCaseModules = module {
    single { EventsUseCase(get()) }
}


val applicationModules = listOf(
    presenterModules,
    useCaseModules,
    repositoryModules,
    databaseModules
)