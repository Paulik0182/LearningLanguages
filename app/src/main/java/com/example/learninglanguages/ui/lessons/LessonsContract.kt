package com.example.learninglanguages.ui.lessons

import androidx.lifecycle.LiveData
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity

interface LessonsContract {

    //это для управления view
    interface View {
        fun showProgress(inProgress: Boolean)
        fun setCourse(lesson: CourseEntity)// данные (показать)
        fun openLesson(lessonEntity: LessonEntity)//открыть урок

    }

    // это для того чтобы была возможность сообщить view о происходящем (некая логика)
    interface Presenter {
        fun attach(view: View)//присоединить view
        fun detach()//отсоединить, уничтожить view

        fun onLessonClick(lessonEntity: LessonEntity)// нажали и передали урок

    }

    interface ViewModel {
        // сразу когда чтото будет кластся в inProgressLiveData, сразу все подписчики будут получать изменения
        val inProgressLiveData: LiveData<Boolean>
        val coursesLiveData: LiveData<List<CourseEntity>>
        val selectedLessonsLiveData: LiveData<LessonEntity>

        fun onLessonClick(lessonEntity: LessonEntity)// нажали и передали урок
    }
}