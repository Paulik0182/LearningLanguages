package com.example.learninglanguages.domain.repos

import com.example.learninglanguages.domain.entities.LessonIdEntity

interface FavoriteLessonsRepo {
    fun addFavorite(lessonId: LessonIdEntity)//добавить урок
    fun removeEntity(lessonId: LessonIdEntity)//удалить урок
    fun getFavorites(): List<LessonIdEntity>//получить урок
}