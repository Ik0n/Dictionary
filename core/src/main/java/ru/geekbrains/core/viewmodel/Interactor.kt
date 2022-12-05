package ru.geekbrains.core.viewmodel

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteResource: Boolean): T
}