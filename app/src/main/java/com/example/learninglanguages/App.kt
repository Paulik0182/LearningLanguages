package com.example.learninglanguages

import android.app.Application
import android.content.Context
import com.example.learninglanguages.dagger.AppComponent
import com.example.learninglanguages.data.FirebaseLessonsRepoImpl
import com.example.learninglanguages.domain.repos.CoursesRepo
import java.util.*


/**
 * Здесь создаем репозиторий. Репо должна быть одна, а не создаватся каждый раз в каждом фрагменте.
 * этот класс для того чтобы воспользоватся application.
 * Необходимо прописать в манифесте данный класс
 * android:name=".App"
 */
class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }


    val coursesRepo: CoursesRepo by lazy {
//        AssetsCoursesRepoImpl(this)
        FirebaseLessonsRepoImpl()
    }

}

// рассширение для Context, суть его в том что он должен всегда должен возвращать Application Component
// здесь идет проверка каой тип является appComponent, если он не является типом App то будет
// запрашиватся у текущего контекста будет запрашиватся свой Application Component и рекурсивно
// возвращатся appComponent
val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }