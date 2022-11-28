package ru.geekbrains.dictionary.di

import androidx.room.Room
import org.koin.dsl.module
import ru.geekbrains.dictionary.history.HistoryInteractor
import ru.geekbrains.dictionary.history.HistoryViewModel
import ru.geekbrains.dictionary.model.data.DataModel
import ru.geekbrains.dictionary.model.database.HistoryDataBase
import ru.geekbrains.dictionary.model.datasource.RetrofitImpl
import ru.geekbrains.dictionary.model.datasource.RoomDataBaseImpl
import ru.geekbrains.dictionary.model.repository.Repository
import ru.geekbrains.dictionary.model.repository.RepositoryImpl
import ru.geekbrains.dictionary.model.repository.RepositoryImplLocal
import ru.geekbrains.dictionary.model.repository.RepositoryLocal
import ru.geekbrains.dictionary.view.base.main.MainInteractor
import ru.geekbrains.dictionary.view.base.main.MainViewModel

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImpl(RetrofitImpl()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplLocal(RoomDataBaseImpl(get()))
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