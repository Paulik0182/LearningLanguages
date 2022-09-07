package com.example.learninglanguages

import android.app.Application
import com.example.learninglanguages.data.FirebaseLessonsRepoImpl
import com.example.learninglanguages.di.appModule
import com.example.learninglanguages.domain.repos.CoursesRepo
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import java.util.*

/**
 * Здесь создаем репозиторий. Репо должна быть одна, а не создаватся каждый раз в каждом фрагменте.
 * этот класс для того чтобы воспользоватся application.
 * Необходимо прописать в манифесте данный класс
 * android:name=".App"
 *
 * здесь необходимо инициализировать KOIN. здесь будет начало работы приложения. стартовая точка приложения onCreate
 */
class App : Application() {

    val coursesRepo: CoursesRepo by lazy {
//        AssetsCoursesRepoImpl(this)
        FirebaseLessonsRepoImpl()
    }

    override fun onCreate() {
        // Any - это базовый объект, это тип для всего. Map это ключ - значение
        val rotationFreeStorage: MutableMap<String, Any> = WeakHashMap()
        val rotationLessonFreeStorage: MutableMap<String, Any> = WeakHashMap()

        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger(Level.DEBUG)//это необходимо для логирования ошибок, что бы все ошибки выводились
            androidContext(this@App)
            modules(appModule)
        }
    }
}