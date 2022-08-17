package com.example.learninglanguages.ui.courses

import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.CoursesRepo

class CoursesPresenter(
    private val coursesRepo: CoursesRepo//Экзепляр класса (достали repo) (ЭТО АРГУМЕНТ КОНСТРУКТОРА. private val - это автоматически делает членом класса)
) : CoursesContract.Presenter {

    // для то чтобы воспользоватся attach view необходимо ее запомнить
    private var view: CoursesContract.View? = null

    override fun attach(view: CoursesContract.View) {
        this.view = view

        view.showProgress(true)
        coursesRepo.getCourses {
            view.showProgress(false)
            view.setCourses(it)// пополнение данных
        }
    }

    override fun detach() {
        view = null
    }

    override fun onLessonClick(lessonEntity: LessonEntity) {
        view?.openLesson(lessonEntity)// реализовываем метод, вызываем урок. view обязательно должна быть с ? (проверка на null)
    }

    override fun onCourseClick(courseEntity: CourseEntity) {
        view?.openCourse(courseEntity)
    }
}