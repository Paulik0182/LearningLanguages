package com.example.learninglanguages.data

import com.example.learninglanguages.domain.entities.LessonIdEntity
import com.example.learninglanguages.domain.repos.FavoriteLessonsRepo

/**
 * Set - это такая сущьность котороя позволяет включать в себя один элемент.
 * То-есть, если он уже есть, в него не будет положено еще раз.
 */

class FavoriteRepoImpl : FavoriteLessonsRepo {

    private val data: MutableSet<LessonIdEntity> = HashSet()//массив сущьностей

    override fun addFavorite(lessonId: LessonIdEntity) {
        data.add(lessonId)//добавили
    }

    override fun removeEntity(lessonId: LessonIdEntity) {
        data.remove(lessonId)//удалили
    }

    override fun getFavorites(): List<LessonIdEntity> {
        return ArrayList(data)//получили
    }
}