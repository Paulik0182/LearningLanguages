package com.example.learninglanguages.ui.lessons

import com.example.learninglanguages.domain.entities.LessonEntity

class LessonsPresenter : LessonsContract.Presenter {

    // для то чтобы воспользоватся attach view необходимо ее запомнить
    private var view: LessonsContract.View? = null

    override fun attach(view: LessonsContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun onLessonClick(lessonEntity: LessonEntity) {
        view?.openLesson(lessonEntity)// реализовываем метод, вызываем урок. view обязательно должна быть с ? (проверка на null)
    }
}