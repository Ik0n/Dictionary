package ru.geekbrains.dictionary.model.datasource

import io.reactivex.Observable
import ru.geekbrains.dictionary.model.data.DataModel

class DataSourceLocal(
    private val localProvider: RoomDataBaseImpl = RoomDataBaseImpl()
) : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> = localProvider.getData(word)
}