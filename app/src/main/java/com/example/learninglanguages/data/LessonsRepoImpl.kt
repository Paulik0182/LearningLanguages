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
            "https://proekt-obk.com/UploadedFiles/Blogs/Categories/2560_3d9c9ccce-6f07-4613-98bf-bfb448c984a9.jpg"
        ),
        LessonEntity(
            2,
            "kotlin_task.json",
            "Kotlin",
            "https://avatars.mds.yandex.net/get-zen_doc/1885679/pub_5e1b791e4e057700b19fdb30_5e1b79526f5f6f00ae02e9ab/scale_1200"
        )
    )
}