package com.example.learninglanguages.domain.repos

import com.example.learninglanguages.domain.entities.CourseEntity

interface CoursesRepo {

    fun getCourses(onSuccess: (MutableList<CourseEntity>) -> Unit)
    fun getCourse(id: Long, onSuccess: (CourseEntity?) -> Unit)

}