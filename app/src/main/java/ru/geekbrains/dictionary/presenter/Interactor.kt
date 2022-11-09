package ru.geekbrains.dictionary.presenter

import io.reactivex.Observable

interface Interactor<T> {
    fun getData(word: String, fromRemoteResource: Boolean): Observable<T>
}