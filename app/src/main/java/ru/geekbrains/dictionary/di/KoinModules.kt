package ru.geekbrains.dictionary.di

import androidx.room.Room
import org.koin.dsl.module

import ru.geekbrains.dictionary.view.base.main.MainInteractor
import ru.geekbrains.dictionary.view.base.main.MainViewModel
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
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}