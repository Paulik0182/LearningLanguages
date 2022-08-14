package com.example.learninglanguages.domain.repos

import com.example.learninglanguages.domain.entities.CourseEntity

interface CoursesRepo {

    fun getCourses(onSuccess: (List<CourseEntity>) -> Unit)
    fun getCourse(id: Long): CourseEntity

}