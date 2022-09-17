package com.example.learninglanguages.domain.interactor

/**
 * Интерактор.
 */

import com.example.learninglanguages.domain.entities.CourseWithFavoriteLessonEntity
import com.example.learninglanguages.domain.entities.FavouriteLessonEntity

interface CoursesWithFavoriteLessonInteractor {
    fun getCourses(onSuccess: (MutableList<CourseWithFavoriteLessonEntity>) -> Unit)
    fun getCourse(id: Long, onSuccess: (CourseWithFavoriteLessonEntity?) -> Unit)
    fun getLesson(courseId: Long, lessonId: Long, onSuccess: (FavouriteLessonEntity?) -> Unit)
}