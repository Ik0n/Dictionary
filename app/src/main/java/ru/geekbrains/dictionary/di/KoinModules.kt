package ru.geekbrains.dictionary.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.geekbrains.dictionary.view.base.main.MainActivity

import ru.geekbrains.dictionary.view.base.main.MainInteractor
import ru.geekbrains.dictionary.view.base.main.MainViewModel
import ru.geekbrains.historyscreen.view.history.HistoryActivity
import ru.geekbrains.historyscreen.view.history.HistoryInteractor
import ru.geekbrains.historyscreen.view.history.HistoryViewModel
import ru.geekbrains.model.DataModel
import ru.geekbrains.repository.*
import ru.geekbrains.repository.room.HistoryDataBase


val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImpl(RetrofitImpl()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplLocal(HistoryDataBaseImpl(get()))
    }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}

val historyScreen = module {
    scope(named<HistoryActivity>()) {
        scoped { HistoryInteractor(get(), get()) }
        viewModel { HistoryViewModel(get()) }
    }
}