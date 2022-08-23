package com.example.learninglanguages.ui.courses

import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity

/**
 * Условно это интерфейс где прописываются некие связи между view и ...
 */

interface CoursesContract {

    //это для управления view
    interface View {
        fun showProgress(inProgress: Boolean)
        fun setCourses(course: MutableList<CourseEntity>)// данные (показать)
        fun openLesson(lessonEntity: LessonEntity)//открыть урок
        fun openCourse(courseEntity: CourseEntity)// открыть курсы

    }

    // это для того чтобы была возможность сообщить view о происходящем (некая логика), это вся логика
    interface Presenter {
        fun attach(view: View)//присоединить view
        fun detach()//отсоединить, уничтожить view

        fun onLessonClick(lessonEntity: LessonEntity)// нажали и передали урок
        fun onCourseClick(courseEntity: CourseEntity)// нажали и передали курс

    }
}