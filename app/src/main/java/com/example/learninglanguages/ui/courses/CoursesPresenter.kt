package com.example.learninglanguages.ui.courses

import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.CoursesRepo

/**
 * Проблемы MVP
 * 1.Знание презентора о view
 * 2.Хранить и востанавливать состояние (все значеия) в аттач
 * 3.Возвращать все вызовы на главвный поток (нарушать "незнание" проезентора
 * 4*. Не поддерживает гугл и платформой (пишем сами или ищем библиотеки)
 */

class CoursesPresenter(
    private val coursesRepo: CoursesRepo//Экзепляр класса (достали repo) (ЭТО АРГУМЕНТ КОНСТРУКТОРА. private val - это автоматически делает членом класса)
) : CoursesContract.Presenter {

    // для то чтобы воспользоватся attach view необходимо ее запомнить
    private var view: CoursesContract.View? = null
    private var inProgress: Boolean = false //кэшируем Progress
        private set(value) {
            field = value
            view?.showProgress(value)
        }
    private var courses: MutableList<CourseEntity> = mutableListOf()
        //Кэшируем курсы
        private set(value) {
            field = value
            view?.setCourses(value)
        }

    override fun attach(view: CoursesContract.View) {
        this.view = view

//        view.showProgress(inProgress)
        inProgress = inProgress
//        val localCourses = courses//еще раз закэшировали курсы
        if (courses.isEmpty()) {// проверка если courses равно null, то мы качаем курсы, если нет то подставляем уже закэшированные курсы
            inProgress = true
            coursesRepo.getCourses {
                inProgress = false
//                view.setCourses(it)// пополнение данных
                courses = it
            }
        } else {
            courses = courses
//            view.setCourses(localCourses)// пополнение данных
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