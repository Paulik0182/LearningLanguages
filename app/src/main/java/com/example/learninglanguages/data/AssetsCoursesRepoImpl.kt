package com.example.learninglanguages.data

import android.content.Context
import com.example.learninglanguages.Key
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.repos.CourseRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AssetsCoursesRepoImpl(
    private val context: Context
) : CourseRepo {


    override fun getCourse(onSuccess: (List<CourseEntity>) -> Unit) {

        val databaseJson: String = context.assets.open(Key.ASSETS_LESSONS_TASK_KEY)
            .bufferedReader().use {
                it.readText()
            }

        //достаем массив уроков
        val courses: List<CourseEntity> =
            Gson()
                .fromJson(databaseJson, object : TypeToken<List<CourseEntity>>() {}
                    .type)
        onSuccess(courses)
    }
}
