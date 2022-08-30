package com.example.learninglanguages.dagger

/**
 * @Component - это какраз анатация обозначающая что этот интерфейс и является компонентом и из него
 * получают зависимости
 */

import com.example.learninglanguages.domain.repos.CoursesRepo
import com.example.learninglanguages.ui.courses.CoursesFragment
import com.example.learninglanguages.ui.courses.CoursesViewModel
import dagger.Component

@Component(modules = [CoursesViewModel::class])
interface AppComponent {

    fun inject(coursesFragment: CoursesFragment)

    val coursesRepo: CoursesRepo

}