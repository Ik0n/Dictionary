package ru.geekbrains.dictionary.view.base.main

import ru.geekbrains.dictionary.model.data.AppState
import ru.geekbrains.dictionary.model.data.DataModel
import ru.geekbrains.dictionary.model.repository.Repository
import ru.geekbrains.dictionary.viewmodel.Interactor

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteResource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteResource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}