package ru.geekbrains.dictionary.presenter

import androidx.lifecycle.LiveData
import io.reactivex.observers.DisposableObserver
import ru.geekbrains.dictionary.model.data.AppState
import ru.geekbrains.dictionary.model.datasource.DataSourceLocal
import ru.geekbrains.dictionary.model.datasource.DataSourceRemote
import ru.geekbrains.dictionary.model.repository.RepositoryImpl
import ru.geekbrains.dictionary.view.base.main.MainInteractor

class MainViewModel(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImpl(DataSourceRemote()),
        RepositoryImpl(DataSourceLocal())
    )
): BaseViewModel<AppState>() {
    private var appState: AppState? = null

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe{ liveDataForViewToObserve.value = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )
        return super.getData(word, isOnline)
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(state: AppState) {
                appState = state
                liveDataForViewToObserve.value = state
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {
            }

        }
    }
}