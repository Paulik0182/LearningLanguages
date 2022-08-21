package com.example.learninglanguages.ui.lessons

import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.CoursesRepo

class LessonsPresenter(
    private val coursesRepo: CoursesRepo,//Экзепляр класса (достали repo) (ЭТО АРГУМЕНТ КОНСТРУКТОРА. private val - это автоматически делает членом класса)
    private val lessonId: Long?
) : LessonsContract.Presenter {

    // для то чтобы воспользоватся attach view необходимо ее запомнить
    private var view: LessonsContract.View? = null

    override fun attach(view: LessonsContract.View) {
        this.view = view

        //Достаем данные
        requireNotNull(lessonId)//сваливаем приложение если придет null (не выполнимое условие)
        coursesRepo.getCourse(lessonId) {
            if (it != null) {
                view.setCourse(it)
            }
        }
    }

    override fun detach() {
        view = null
    }

    override fun onLessonClick(lessonEntity: LessonEntity) {
        view?.openLesson(lessonEntity)// реализовываем метод, вызываем урок. view обязательно должна быть с ? (проверка на null)
    }
}