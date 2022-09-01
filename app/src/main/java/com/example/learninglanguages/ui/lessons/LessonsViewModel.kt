package com.example.learninglanguages.ui.lessons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.CoursesRepo
import com.example.learninglanguages.utils.SingleLiveEvent

class LessonsViewModel(
    private val coursesRepo: CoursesRepo,
    private val lessonId: Long
) : ViewModel() {

    //Сделали класс Factory (это объект Фабрика) в которую кладем внутрь модели
    class Factory(private val coursesRepo: CoursesRepo, private val lessonId: Long) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LessonsViewModel(coursesRepo, lessonId) as T
        }
    }

    //одно из решений над Mutable (это стандартно принятый этот метод)
    private val _inProgressLiveData: MutableLiveData<Boolean> = MutableLiveData()

    // сразу когда чтото будет кластся в inProgressLiveData, сразу все подписчики будут получать изменения
    val inProgressLiveData: LiveData<Boolean> = _inProgressLiveData
    val coursesLiveData: LiveData<CourseEntity> = MutableLiveData()
    val selectedLessonsLiveData: LiveData<LessonEntity> = SingleLiveEvent()

    init {
        //проверяе на наличие данных в coursesLiveData. Это необходимо для того чтобы при повороте данные не закачивались заново (это костыль)
        if (coursesLiveData.value == null) {
            _inProgressLiveData.postValue(true)
            coursesRepo.getCourse(lessonId) {
                it?.let {
                    inProgressLiveData.mutable().postValue(false)
                    coursesLiveData.mutable().postValue(it)
                }
            }
        }
    }

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