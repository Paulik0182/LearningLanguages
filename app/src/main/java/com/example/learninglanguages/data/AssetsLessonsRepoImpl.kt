package com.example.learninglanguages.data

import android.content.Context
import com.example.learninglanguages.Key
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.LessonRepo

class AssetsLessonsRepoImpl(
    private val context: Context
) : LessonRepo {

    //в данном случае мы не можем добавлять новые значения в репозиторий
    override fun addLesson(taskEntity: LessonEntity) {
        throw IllegalStateException("Нельзя добавлять в репозиторий новые элементы")
    }

    override fun getLessons(): List<LessonEntity> {
        // В kotlin все колекции не изменяемые и их нужно преобразовывать на изменяемые. в реализации List нет методов add и т.д.
        val lessons = emptyList<LessonEntity>().toMutableList() // список уроков
        var i: Long = 0
        //считываем папку assets
        val filesNameList = context.assets.list(Key.ASSETS_LESSONS_DIR_NAME_KEY)
        //проходим по папке asset
        filesNameList?.forEach { fileName ->
            val lesson = LessonEntity(
                i++,
                "$Key.ASSETS_LESSONS_DIR_NAME_KEY/$fileName",
                fileName,
                "https://i.pinimg.com/originals/22/9e/03/" +
                        "229e031f9d93ddaf3987bed71ddc012e.png"
            )
            lessons.add(lesson)// кладем полученные данные
        }
        return lessons
    }
}