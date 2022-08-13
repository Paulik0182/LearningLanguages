package com.example.learninglanguages

import android.app.Application
import com.example.learninglanguages.data.AssetsCoursesRepoImpl
import com.example.learninglanguages.domain.repos.CourseRepo

/**
 * Здесь создаем репозиторий. Репо должна быть одна, а не создаватся каждый раз в каждом фрагменте.
 * этот класс для того чтобы воспользоватся application.
 * Необходимо прописать в манифесте данный класс
 * android:name=".App"
 */
class App : Application() {

    val courseRepo: CourseRepo by lazy {
        AssetsCoursesRepoImpl(this)
    }
}