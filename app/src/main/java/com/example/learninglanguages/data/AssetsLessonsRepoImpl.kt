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

    override fun getLessons(): List<LessonEntity> = context.assets.list(ASSETS_LESSONS_DIR_NAME_KEY)
        ?.map { fileName ->
            //обращаемся ко всем файлам в asset
            //проходим по папке asset/lesson и распознаем файлы и преобразуем в объект
            val rawJson: String = context.assets
                .open("$ASSETS_LESSONS_DIR_NAME_KEY/$fileName")
                .bufferedReader().use {
                    it.readText()
                }
            //преобразуем в объект
            return@map Gson().fromJson(rawJson, LessonEntity::class.java)
        } ?: emptyList()

    override fun getLessons(onSuccess: (List<LessonEntity>) -> Unit) {
        TODO("Not yet implemented")
    }
}
