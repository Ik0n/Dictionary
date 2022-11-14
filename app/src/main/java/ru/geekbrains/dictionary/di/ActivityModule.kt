package ru.geekbrains.dictionary.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.geekbrains.dictionary.view.base.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}