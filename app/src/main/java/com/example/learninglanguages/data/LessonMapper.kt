package com.example.learninglanguages.data

import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.CourseWithFavoriteLessonEntity
import com.example.learninglanguages.domain.entities.FavouriteLessonEntity
import com.example.learninglanguages.domain.entities.LessonEntity

/**
 * экстэншены. Копируем один объект в другой.
 * Мы здесь скопировали сущьность (преобразовали) и добавили (модифицировали)
 * старую сущьность новыми полями.
 */

fun LessonEntity.mapToFavoriteLesson(isFavorite: Boolean = false): FavouriteLessonEntity {
    return FavouriteLessonEntity(
        id = this.id,
        name = name,
        imageUrl = imageUrl,
        victoryImageUrl = victoryImageUrl,
        tasks = ArrayList(tasks),//сделали копию листа
        isFavorite = isFavorite
    )
}

fun CourseEntity.mapToCourseWithFavoriteLessonEntity(): CourseWithFavoriteLessonEntity {
    return CourseWithFavoriteLessonEntity(
        id = id,
        name = name,
        logoUrl = logoUrl,
        //у lessons другой тип, поэтому делаем преобразование к соответствующему типу
        lessons = lessons.map { it.mapToFavoriteLesson() }.toMutableList()
        //берем список, для каждого члена списка применяем преобразование -> it.mapToFavoriteLesson(),
        // долее превращаем в общий список -> toMutableList()
        // Очень важный момент - не передаем факт избранности
    )
}