package ru.geekbrains.dictionary

import android.app.Application
import dagger.android.*
import dagger.android.support.DaggerAppCompatActivity
import ru.geekbrains.dictionary.di.AppComponent
import ru.geekbrains.dictionary.di.DaggerAppComponent
import javax.inject.Inject

class TranslatorApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}