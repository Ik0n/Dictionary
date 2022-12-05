package ru.geekbrains.historyscreen.view.history

import ru.geekbrains.core.viewmodel.Interactor
import ru.geekbrains.model.AppState
import ru.geekbrains.model.DataModel
import ru.geekbrains.repository.Repository
import ru.geekbrains.repository.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
): Interactor<AppState> {
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