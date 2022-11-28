package ru.geekbrains.dictionary.model.datasource

import ru.geekbrains.dictionary.model.data.AppState
import ru.geekbrains.dictionary.model.data.DataModel
import ru.geekbrains.dictionary.model.database.HistoryDao
import ru.geekbrains.dictionary.utils.convertDataModelSuccessToEntity
import ru.geekbrains.dictionary.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImpl(private val historyDao: HistoryDao) : DataSourceLocal<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}