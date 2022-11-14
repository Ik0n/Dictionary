package ru.geekbrains.dictionary.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.dictionary.model.data.AppState
import ru.geekbrains.dictionary.presenter.BaseViewModel
import ru.geekbrains.dictionary.presenter.Presenter

abstract class BaseActivity<T: AppState> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)

}