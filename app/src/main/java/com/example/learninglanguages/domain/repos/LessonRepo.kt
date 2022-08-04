package com.example.learninglanguages.domain.repos

import com.example.learninglanguages.domain.entities.LessonEntity

interface LessonRepo {
    fun addLesson(taskEntity: LessonEntity)
    fun getLessons(): List<LessonEntity>
}