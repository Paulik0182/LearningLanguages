package com.example.learninglanguages.ui.lessons

import androidx.fragment.app.Fragment
import com.example.learninglanguages.Key
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.CoursesRepo

class LessonsPresenter(
    private val coursesRepo: CoursesRepo//Экзепляр класса (достали repo) (ЭТО АРГУМЕНТ КОНСТРУКТОРА. private val - это автоматически делает членом класса)
) : Fragment(), LessonsContract.Presenter {

    // для то чтобы воспользоватся attach view необходимо ее запомнить
    private var view: LessonsContract.View? = null

    override fun attach(view: LessonsContract.View) {
        this.view = view

        //Достаем данные
        val courseId = arguments?.getLong(Key.COURSE_ID_ARGS_KEY)
        requireNotNull(courseId)//сваливаем приложение если придет null (не выполнимое условие)
        coursesRepo.getCourse(courseId) {
            view.setCourse((it?.lessons ?: Unit) as CourseEntity)//  пополнение адаптера данными
        }
    }

    override fun detach() {
        view = null
    }

    override fun onLessonClick(lessonEntity: LessonEntity) {
        view?.openLesson(lessonEntity)// реализовываем метод, вызываем урок. view обязательно должна быть с ? (проверка на null)
    }
}