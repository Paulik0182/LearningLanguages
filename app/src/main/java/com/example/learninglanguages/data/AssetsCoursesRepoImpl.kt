package com.example.learninglanguages.data

import android.content.Context
import com.example.learninglanguages.Key.ASSETS_LESSONS_TASK_KEY
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.repos.CoursesRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AssetsCoursesRepoImpl(
    private val context: Context
) : CoursesRepo {

    override fun getCourses(onSuccess: (MutableList<CourseEntity>) -> Unit) {

        val databaseJson: String = context.assets.open(ASSETS_LESSONS_TASK_KEY)
            .bufferedReader()
            .use {
                it.readText()
            }

        //достаем массив уроков
        val courses: MutableList<CourseEntity> =
            Gson()
                .fromJson(databaseJson, object : TypeToken<List<CourseEntity>>() {}
                    .type)
        onSuccess(courses)
    }

    //подгружаем данные по ID
    override fun getCourse(id: Long, onSuccess: (CourseEntity?) -> Unit) {
        getCourses {
            val searchResult = it.find { it.id == id }
            onSuccess.invoke(searchResult)
        }
    }
}
