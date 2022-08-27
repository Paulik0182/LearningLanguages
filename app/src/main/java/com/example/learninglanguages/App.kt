package com.example.learninglanguages

import android.app.Application
import com.example.learninglanguages.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

/**
 * Здесь создаем репозиторий. Репо должна быть одна, а не создаватся каждый раз в каждом фрагменте.
 * этот класс для того чтобы воспользоватся application.
 * Необходимо прописать в манифесте данный класс
 * android:name=".App"
 *
 * здесь необходимо инициализировать KOIN. здесь будет начало работы приложения. стартовая точка приложения onCreate
 */
class App : Application() {

    override fun onCreate() {

        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}