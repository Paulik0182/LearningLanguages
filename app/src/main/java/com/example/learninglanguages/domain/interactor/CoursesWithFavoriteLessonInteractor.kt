package com.example.learninglanguages.domain.interactor

/**
 * Интерактор (interactor).
 * Интерактор можно представить как «модели / контроллере». Интерактор будет извлекать данные
 * из базы данных, веб-служб или любого другого источника данных (Repository).
 * После получения данных интерактор отправит данные в Presenter.
 * Преимущества использования интерактора в отдельном классе заключаются в том,
 * что он отделяет класс, делая его более чистым и тестируемым.
 * Можете поместить логику интерактора в класс презентатора, но в этом нет смысла.
 */

import com.example.learninglanguages.domain.entities.CourseWithFavoriteLessonEntity
import com.example.learninglanguages.domain.entities.FavouriteLessonEntity

interface CoursesWithFavoriteLessonInteractor {
    fun getCourses(onSuccess: (MutableList<CourseWithFavoriteLessonEntity>) -> Unit)
    fun getCourse(id: Long, onSuccess: (CourseWithFavoriteLessonEntity?) -> Unit)
    fun getLesson(courseId: Long, lessonId: Long, onSuccess: (FavouriteLessonEntity?) -> Unit)
}