package ru.geekbrains.dictionary.view.base.main

import io.reactivex.Observable
import ru.geekbrains.dictionary.model.data.DataModel
import ru.geekbrains.dictionary.model.data.AppState
import ru.geekbrains.dictionary.presenter.Interactor
import ru.geekbrains.dictionary.model.repository.Repository

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteResource: Boolean): Observable<AppState> {
        return if (fromRemoteResource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}