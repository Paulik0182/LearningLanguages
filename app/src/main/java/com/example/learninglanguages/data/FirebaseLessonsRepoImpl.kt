package com.example.learninglanguages.data

import com.example.learninglanguages.Key
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.repos.CoursesRepo
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseLessonsRepoImpl : CoursesRepo {

//    private var data: List<LessonEntity> = emptyList()

    //от этого кода можно отказатся
    //в данном случае мы не можем добавлять новые значения в репозиторий
//    override fun addLesson(taskEntity: LessonEntity) {
//        throw IllegalStateException("Нельзя добавлять в репозиторий новые элементы")
//    }

    override fun getCourses(onSuccess: (List<CourseEntity>) -> Unit) {
        //ссылка на бд. reference - это ссылка на значение
        val database = Firebase.database(Key.DATABASE_URL_KEY)

        database.setPersistenceEnabled(true)//для возможной работы без интернета
        database.reference.keepSynced(true)//для синхранизации данных (кешируем данные)

        database.reference.get()
            .addOnSuccessListener {
                val lessons: MutableList<CourseEntity> = mutableListOf()// собираем колекцию
                it.children.forEach { snapshot ->
                    //обработка исключения. Если есть соответствующие данные то обрабатываем,
                    // если данные не соответствуют то ничего с ними не делаем
                    try {
                        //Парсим значения. Если значение не null то добавляем в колекцию (lessons.add(it) )
                        snapshot.getValue(CourseEntity::class.java)?.let { lesson ->
                            lessons.add(lesson)
                        }
                        //ничего с ними не делаем
                    } catch (exc: DatabaseException) {
                        exc.printStackTrace()
                    }
                }
                onSuccess.invoke(lessons)
            }

    }

    override fun getCourse(id: Long): CourseEntity {
        TODO("Not yet implemented")
    }

//    override fun getLessons(): List<LessonEntity> = data
}
