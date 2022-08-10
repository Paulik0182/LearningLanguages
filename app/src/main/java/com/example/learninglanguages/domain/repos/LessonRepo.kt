package com.example.learninglanguages.domain.repos

import com.example.learninglanguages.domain.entities.LessonEntity

interface LessonRepo {
    fun addLesson(taskEntity: LessonEntity)

    fun getLessons(): List<LessonEntity>

    //должны получать колбык (как в сетевом взаимодействии)
    fun getLessons(onSuccess: (List<LessonEntity>) -> Unit)

    //onSuccess: принимает на вход (List<LessonEntity>)
}