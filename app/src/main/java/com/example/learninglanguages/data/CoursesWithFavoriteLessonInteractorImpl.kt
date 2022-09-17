package com.example.learninglanguages.data

/**
 * необходимо получить курсы и лайки
 * нужны два репозитория на вход
 */
import com.example.learninglanguages.domain.entities.CourseWithFavoriteLessonEntity
import com.example.learninglanguages.domain.entities.FavouriteLessonEntity
import com.example.learninglanguages.domain.interactor.CoursesWithFavoriteLessonInteractor
import com.example.learninglanguages.domain.repos.CoursesRepo
import com.example.learninglanguages.domain.repos.FavoriteLessonsRepo

class CoursesWithFavoriteLessonInteractorImpl(
    private val coursesRepo: CoursesRepo,
    private val favoriteRepo: FavoriteLessonsRepo
) : CoursesWithFavoriteLessonInteractor {

    override fun getCourses(onSuccess: (MutableList<CourseWithFavoriteLessonEntity>) -> Unit) {
        coursesRepo.getCourses { courseList ->
            onSuccess(courseList.map { it.mapToCourseWithFavoriteLessonEntity() }.toMutableList())
        }
    }

    override fun getCourse(id: Long, onSuccess: (CourseWithFavoriteLessonEntity?) -> Unit) {
        coursesRepo.getCourse(id) {
            onSuccess(it?.mapToCourseWithFavoriteLessonEntity())
        }
    }

    override fun getLesson(
        courseId: Long,
        lessonId: Long,
        onSuccess: (FavouriteLessonEntity?) -> Unit
    ) {
        coursesRepo.getLesson(courseId, lessonId) {
            onSuccess(it?.mapToFavoriteLesson())
        }
    }
}