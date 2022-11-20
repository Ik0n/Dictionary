package ru.geekbrains.dictionary.viewmodel

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteResource: Boolean): T
}