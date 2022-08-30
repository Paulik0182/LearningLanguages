package com.example.learninglanguages.di

/**
 * этот класс некое объединение зависимостей в общею "кучу, пакет"
 *
 * Зависимость - это любая сложная сущьность которую нужно создавать (репозитории ....)
 *
 * single (одиночный объект) — создается объект, который сохраняется в течение всего периода существования контейнера (аналогично синглтону)
 * factory (фабрика объектов) — каждый раз создается новый объект, без сохранения в контейнере (совместное использование невозможно)
 * scoped (объект в области) — создается объект, который сохраняется в рамках периода существования связанной временной области.
 *
 * get— разрешает компонентные зависимости. get() для получения необходимой зависимости из контейнера Koin.
 * get - ходит по всем зависимостям и ищит ту зависимость которая поможет ему вернуть значение.
 */

import com.example.learninglanguages.data.FirebaseLessonsRepoImpl
import com.example.learninglanguages.domain.repos.CoursesRepo
import com.example.learninglanguages.ui.courses.CoursesViewModel
import com.example.learninglanguages.ui.lessons.LessonFragment
import com.example.learninglanguages.ui.lessons.LessonsViewModel
import com.example.learninglanguages.ui.task.answer.TasksViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<CoursesRepo> { FirebaseLessonsRepoImpl() }
//    single<CoursesRepo> {
//        AssetsCoursesRepoImpl(context = get()) }

    //секция viewModel
    viewModel { CoursesViewModel(get()) }
    viewModel { parameters -> LessonsViewModel(get(), parameters.get()) }
    viewModel { TasksViewModel(get()) }

    //Секция для фрагментов
    fragment { LessonFragment() }
}