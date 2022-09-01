package com.example.learninglanguages

import android.app.Application
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

    val coursesRepo: CoursesRepo by lazy {
//        AssetsCoursesRepoImpl(this)
        FirebaseLessonsRepoImpl()
    }

    // Any - это базовый объект, это тип для всего. Map это ключ - значение
    val rotationFreeStorage: MutableMap<String, Any> = WeakHashMap()
    val rotationLessonFreeStorage: MutableMap<String, Any> = WeakHashMap()
}