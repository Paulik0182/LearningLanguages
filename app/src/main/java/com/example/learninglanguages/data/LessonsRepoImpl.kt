package com.example.learninglanguages.data

import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.LessonRepo


class LessonsRepoImpl(

) : LessonRepo {

    private lateinit var data: List<LessonEntity>

    //в данном случае мы не можем добавлять новые значения в репозиторий
    override fun addLesson(lessonEntity: LessonEntity) {
        throw IllegalStateException("Нельзя добавлять в репозиторий новые элементы")
    }

    //вариант написания 2. чтобы не создавали новых записей
//    override fun addLesson(lessonEntity: LessonEntity = Unit

    //    override fun getLessons(): List<LessonEntity> = ArrayList(data)
    override fun getLessons(): List<LessonEntity> = listOf(
        LessonEntity(
            1,
            "english_task.json",
            "English",
            "https://miro.medium.com/max/1200/1*C7t9v834_fJtFnHa9CSJbQ.jpeg"
        ),
        LessonEntity(
            2,
            "kotlin_task.json",
            "Kotlin",
            "https://kotlinarabic.files.wordpress.com/2017/05/logo-text1.png?w=1629"
        )
    )
}