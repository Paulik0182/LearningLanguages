package com.example.learninglanguages.ui.lessons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.CoursesRepo
import com.example.learninglanguages.utils.SingleLiveEvent

class LessonsViewModel(
    private val lessonId: Long
) : ViewModel() {

    //одно из решений над Mutable (это стандартно принятый этот метод)
    private val _inProgressLiveData: MutableLiveData<Boolean> = MutableLiveData()

    // сразу когда чтото будет кластся в inProgressLiveData, сразу все подписчики будут получать изменения
    val inProgressLiveData: LiveData<Boolean> = _inProgressLiveData
    val coursesLiveData: LiveData<List<CourseEntity>> = MutableLiveData()
    val selectedLessonsLiveData: LiveData<LessonEntity> = SingleLiveEvent()

//    // для то чтобы воспользоватся attach view необходимо ее запомнить
//    private var view: LessonsContract.View? = null
//    private var inProgress: Boolean = false //кэшируем Progress
//        private set(value) {
//            field = value
//            view?.showProgress(value)
//        }

    fun setLessonsRepo(coursesRepo: CoursesRepo) {
        //проверяе на наличие данных в coursesLiveData. Это необходимо для того чтобы при повороте не данные не закачивались заново (это костыль)
        if (coursesLiveData.value == null) {
            _inProgressLiveData.postValue(true)
            coursesRepo.getCourse(lessonId) {
                it?.let {
                    inProgressLiveData.mutable().postValue(false)
                    coursesLiveData.mutable().postValue(listOf(it))
                }
            }
        }
    }

//    fun setLessonRepo(lessonRepo: LessonRepo) {
//        coursesRepo.getCourse(lessonId) {
//            it?.let {
//                view?.setCourse(it)
//                inProgress = false
//            }
//        }
//    }

//    override fun attach(view: LessonsContract.View) {
//        this.view = view
//
//        inProgress = true
//        //Достаем данные

//    }

    fun onLessonClick(lessonEntity: LessonEntity) {
        (selectedLessonsLiveData as MutableLiveData).value =
            lessonEntity//Вариант когда агресивно приводим к MutableLiveData
    }

    //экстеншен (расширение обычной чужай функции). Можно указать mutable расширение и оно вернет версию MutableLiveData
    //это сделано чтобы случайно во фрагменте случайно не изменить список (в этом рельной безописности нет)
    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as MutableLiveData
    }
}