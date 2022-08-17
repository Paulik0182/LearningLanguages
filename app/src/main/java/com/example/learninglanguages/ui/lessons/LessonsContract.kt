package com.example.learninglanguages.ui.lessons

import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity

interface LessonsContract {

    //это для управления view
    interface View {
        fun setCourses(lesson: CourseEntity)// данные (показать)
        fun openLesson(lessonEntity: LessonEntity)//открыть урок

    }

    // это для того чтобы была возможность сообщить view о происходящем (некая логика)
    interface Presenter {
        fun attach(view: View)//присоединить view
        fun detach()//отсоединить, уничтожить view

        fun onLessonClick(lessonEntity: LessonEntity)// нажали и передали урок

    }
}