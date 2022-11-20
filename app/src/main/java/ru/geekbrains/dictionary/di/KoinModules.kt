package ru.geekbrains.dictionary.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.geekbrains.dictionary.model.data.DataModel
import ru.geekbrains.dictionary.model.datasource.RetrofitImpl
import ru.geekbrains.dictionary.model.datasource.RoomDataBaseImpl
import ru.geekbrains.dictionary.model.repository.Repository
import ru.geekbrains.dictionary.model.repository.RepositoryImpl
import ru.geekbrains.dictionary.view.base.main.MainInteractor
import ru.geekbrains.dictionary.view.base.main.MainViewModel

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImpl(RetrofitImpl())
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImpl(RoomDataBaseImpl())
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}