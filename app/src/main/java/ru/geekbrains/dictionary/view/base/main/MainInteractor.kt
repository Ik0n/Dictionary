package ru.geekbrains.dictionary.view.base.main

import io.reactivex.Observable
import ru.geekbrains.dictionary.di.NAME_LOCAL
import ru.geekbrains.dictionary.di.NAME_REMOTE
import ru.geekbrains.dictionary.model.data.DataModel
import ru.geekbrains.dictionary.model.data.AppState
import ru.geekbrains.dictionary.presenter.Interactor
import ru.geekbrains.dictionary.model.repository.Repository
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val repositoryRemote: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteResource: Boolean): Observable<AppState> {
        return if (fromRemoteResource) {
            repositoryRemote.getData(word).map { AppState.Success(it) }
        } else {
            repositoryLocal.getData(word).map { AppState.Success(it) }
        }
    }
}