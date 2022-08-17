package com.example.learninglanguages.ui.lessons

import com.example.learninglanguages.domain.entities.LessonEntity

interface LessonsContract {

    //это для управления view
    interface View {
        fun setCourses(lesson: List<LessonEntity>)// данные (показать)
        fun openLesson(lessonEntity: LessonEntity)//открыть урок
//        fun openCourse(courseEntity: CourseEntity)// открыть курсы

    }

    // это для того чтобы была возможность сообщить view о происходящем (некая логика)
    interface Presenter {
        fun attach(view: View)//присоединить view
        fun detach()//отсоединить, уничтожить view

        fun onLessonClick(lessonEntity: LessonEntity)// нажали и передали урок
//        fun onCourseClick(courseEntity: CourseEntity)// нажали и передали курс

    }
}