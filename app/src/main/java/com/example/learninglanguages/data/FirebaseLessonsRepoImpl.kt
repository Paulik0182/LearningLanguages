package com.example.learninglanguages.data

import android.os.Handler
import android.os.Looper
import com.example.learninglanguages.Key
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.repos.CoursesRepo
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseLessonsRepoImpl : CoursesRepo {

    //ссылка на бд. reference - это ссылка на значение
    private val database by lazy {
        Firebase.database(Key.DATABASE_URL_KEY).apply { setPersistenceEnabled(true) }
    }    //setPersistenceEnabled(true) для работы без интернета

    override fun getCourses(onSuccess: (MutableList<CourseEntity>) -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({

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
        }, 3_000)
    }

    override fun getCourse(id: Long, onSuccess: (CourseEntity?) -> Unit) {
        getCourses { courseEntity ->
            val searchResult = courseEntity.find { it.id == id }
            onSuccess.invoke(searchResult)
        }
    }
}
