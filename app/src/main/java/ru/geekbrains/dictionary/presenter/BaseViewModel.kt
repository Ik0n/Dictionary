package ru.geekbrains.dictionary.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.geekbrains.dictionary.model.data.AppState
import ru.geekbrains.dictionary.rx.SchedulerProvider

abstract class BaseViewModel<T: AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : ViewModel() {
    open fun getData(word: String, isOnline: Boolean): LiveData<T> = liveDataForViewToObserve
    override fun onCleared() {
        compositeDisposable.clear()
    }
}