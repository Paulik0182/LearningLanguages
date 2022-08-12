package com.example.learninglanguages.domain.repos

import com.example.learninglanguages.domain.entities.CourseEntity

interface CourseRepo {

    fun getCourse(onSuccess: (List<CourseEntity>) -> Unit)

}