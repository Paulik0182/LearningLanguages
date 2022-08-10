package com.example.learninglanguages.data

import com.example.learninglanguages.Key
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.LessonRepo
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseLessonsRepoImpl : LessonRepo {

    private var data: List<LessonEntity> = emptyList()

    private var successListener: ((List<LessonEntity>) -> Unit)? = null

    //в данном случае мы не можем добавлять новые значения в репозиторий
    override fun addLesson(taskEntity: LessonEntity) {
        throw IllegalStateException("Нельзя добавлять в репозиторий новые элементы")
    }

    override fun getLessons(onSuccess: (List<LessonEntity>) -> Unit) {
        //ссылка на бд
        Firebase.database(Key.DATABASE_URL_KEY)
            .reference
            .get()
            .addOnSuccessListener {
                val lessons: MutableList<LessonEntity> = mutableListOf()// собираем колекцию
                it.children.forEach { snapshot ->
                    //обработка исключения. Если есть соответствующие данные то обрабатываем,
                    // если данные не соответствуют то ничего с ними не делаем
                    try {
                        //Парсим значения. Если значение не null то добавляем в колекцию (lessons.add(it) )
                        snapshot.getValue(LessonEntity::class.java)?.let { lesson ->
                            lessons.add(lesson)
                        }
                        //ничего с ними не делаем
                    } catch (exc: DatabaseException) {
                        exc.printStackTrace()
                    }
                }
                data = lessons
                successListener?.invoke(data)
            }
    }

    override fun getLessons(): List<LessonEntity> = data
}
