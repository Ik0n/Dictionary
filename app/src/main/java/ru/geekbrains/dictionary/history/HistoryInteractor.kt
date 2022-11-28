package ru.geekbrains.dictionary.history

import ru.geekbrains.dictionary.model.data.AppState
import ru.geekbrains.dictionary.model.data.DataModel
import ru.geekbrains.dictionary.model.repository.Repository
import ru.geekbrains.dictionary.model.repository.RepositoryLocal
import ru.geekbrains.dictionary.viewmodel.Interactor

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