package com.example.learninglanguages.di

/**
 * этот класс некое объединение зависимостей в общею "кучу, пакет"
 *
 * single (одиночный объект) — создается объект, который сохраняется в течение всего периода существования контейнера (аналогично синглтону)
 * factory (фабрика объектов) — каждый раз создается новый объект, без сохранения в контейнере (совместное использование невозможно)
 * scoped (объект в области) — создается объект, который сохраняется в рамках периода существования связанной временной области.
 *
 * get— разрешает компонентные зависимости. get() для получения необходимой зависимости из контейнера Koin.
 */

import com.example.learninglanguages.data.FirebaseLessonsRepoImpl
import com.example.learninglanguages.domain.repos.CoursesRepo
import com.example.learninglanguages.ui.courses.CoursesViewModel
import com.example.learninglanguages.ui.lessons.LessonsViewModel
import com.example.learninglanguages.ui.task.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<CoursesRepo> { FirebaseLessonsRepoImpl() }
//    single<CoursesRepo> { AssetsCoursesRepoImpl(get()) }

    //секция viewModel
    viewModel { CoursesViewModel(get()) }
    viewModel { parameters -> LessonsViewModel(get(), parameters.get()) }
    viewModel { parameters ->
        TaskViewModel(
            get(),
            parameters[0],
            parameters[1]
        )
    }
}