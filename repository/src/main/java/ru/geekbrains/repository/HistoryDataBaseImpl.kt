package ru.geekbrains.repository


import ru.geekbrains.repository.room.HistoryDao
import ru.geekbrains.model.AppState
import ru.geekbrains.model.DataModel

class HistoryDataBaseImpl(private val historyDao: HistoryDao) : DataSourceLocal<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}