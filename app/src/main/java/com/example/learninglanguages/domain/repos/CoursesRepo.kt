package com.example.learninglanguages.domain.repos

import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity

interface CoursesRepo {

    fun getCourses(onSuccess: (MutableList<CourseEntity>) -> Unit)
    fun getCourse(id: Long, onSuccess: (CourseEntity?) -> Unit)
    fun getLesson(courseId: Long, lessonId: Long, onSuccess: (LessonEntity?) -> Unit)

}