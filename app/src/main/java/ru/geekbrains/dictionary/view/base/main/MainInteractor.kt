package ru.geekbrains.dictionary.view.base.main

import ru.geekbrains.dictionary.model.data.AppState
import ru.geekbrains.dictionary.model.data.DataModel
import ru.geekbrains.dictionary.model.repository.Repository
import ru.geekbrains.dictionary.model.repository.RepositoryLocal
import ru.geekbrains.dictionary.viewmodel.Interactor

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteResource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteResource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}