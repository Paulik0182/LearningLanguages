package com.example.learninglanguages

import android.app.Application
import com.example.learninglanguages.data.AssetsTaskRepoImpl
import com.example.learninglanguages.domain.repos.TaskRepo

/**
 * Здесь создаем репозиторий. Репо должна быть одна, а не создаватся каждый раз в каждом фрагменте.
 * этот класс для того чтобы воспользоватся application.
 * Необходимо прописать в манифесте данный класс
 * android:name=".App"
 */
class App : Application() {

    val englishAssetsTaskRepo: TaskRepo by lazy {
        AssetsTaskRepoImpl(
            this,
            "english_task.json"
        )
    }

    val kotlinAssetsTaskRepo: TaskRepo by lazy {
        AssetsTaskRepoImpl(
            this,
            "kotlin_task.json"
        )
    }
}