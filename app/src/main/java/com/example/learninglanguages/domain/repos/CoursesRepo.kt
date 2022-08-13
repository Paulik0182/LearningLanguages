package com.example.learninglanguages.domain.repos

import com.example.learninglanguages.domain.entities.CourseEntity

interface CoursesRepo {

    fun getCourse(onSuccess: (List<CourseEntity>) -> Unit)

}