package ru.geekbrains.dictionary.view.base.main

import ru.geekbrains.core.viewmodel.Interactor
import ru.geekbrains.model.AppState
import ru.geekbrains.model.DataModel
import ru.geekbrains.repository.Repository
import ru.geekbrains.repository.RepositoryLocal

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