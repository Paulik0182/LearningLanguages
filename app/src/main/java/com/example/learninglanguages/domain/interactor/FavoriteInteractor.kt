package com.example.learninglanguages.domain.interactor

import com.example.learninglanguages.domain.entities.LessonIdEntity

interface FavoriteInteractor {
    fun onLikeChange(lessonIdEntity: LessonIdEntity, callback: (Boolean) -> Unit)
    fun changeLike(lessonIdEntity: LessonIdEntity)
}