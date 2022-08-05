package com.example.learninglanguages.data

import android.content.Context
import com.example.learninglanguages.Key.ASSETS_LESSONS_DIR_NAME_KEY
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.LessonRepo
import com.google.gson.Gson

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
        //считываем папку assets/lesson
        val filesNameList = context.assets.list(ASSETS_LESSONS_DIR_NAME_KEY)
        //проходим по папке asset/lesson
        filesNameList?.forEach { fileName ->
            val rawJson: String = context.assets
                .open("$ASSETS_LESSONS_DIR_NAME_KEY/$fileName")
                .bufferedReader().use {
                    it.readText()
                }

            val lesson = Gson().fromJson(rawJson, LessonEntity::class.java)

            lessons.add(lesson)// кладем полученные данные
        }
        return lessons
    }
}