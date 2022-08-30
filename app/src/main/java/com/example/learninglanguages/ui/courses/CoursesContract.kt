package com.example.learninglanguages.ui.courses

import androidx.lifecycle.LiveData
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity

/**
 * Условно это интерфейс где прописываются некие связи между view и ...
 * liveData - это такой тип данных который позволяет подписатся на него и все время получать изменения
 */

interface CoursesContract {

    interface ViewModel {
        // сразу когда чтото будет кластся в inProgressLiveData, сразу все подписчики будут получать изменения
        val inProgressLiveData: LiveData<Boolean>
        val coursesLiveData: LiveData<List<CourseEntity>>
        val selectedLessonsLiveData: LiveData<LessonEntity>
        val selectedCoursesLiveData: LiveData<CourseEntity>

        fun onLessonClick(lessonEntity: LessonEntity)// нажали и передали урок
        fun onCourseClick(courseEntity: CourseEntity)// нажали и передали курс
    }
}