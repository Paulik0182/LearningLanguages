package com.example.learninglanguages.ui.courses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.CoursesRepo
import com.example.learninglanguages.utils.SingleLiveEvent

/**
 * Проблемы MVP
 * 1.Знание презентора о view
 * 2.Хранить и востанавливать состояние (все значеия) в аттач
 * 3.Возвращать все вызовы на главвный поток (нарушать "незнание" проезентора
 * 4*. Не поддерживает гугл и платформой (пишем сами или ищем библиотеки)
 *
 * liveData - это такой тип данных который позволяет подписатся на него и все время получать изменения
 * Тмкже liveData умеет кэшировать значения
 *
 * liveData нельзя создать (это абстрактный класс), но у него есть несколько наследников
 */

class CoursesViewModel() : ViewModel() {

    //одно из решений над Mutable (это стандартно принятый этот метод)
    private val _inProgressLiveData: MutableLiveData<Boolean> = MutableLiveData()

    // сразу когда чтото будет кластся в inProgressLiveData, сразу все подписчики будут получать изменения
    val inProgressLiveData: LiveData<Boolean> = _inProgressLiveData
    val coursesLiveData: LiveData<List<CourseEntity>> = MutableLiveData()
    val selectedLessonsLiveData: LiveData<LessonEntity> = SingleLiveEvent()
    val selectedCoursesLiveData: LiveData<CourseEntity> = SingleLiveEvent()

    fun setCoursesRepo(coursesRepo: CoursesRepo) {
        //проверяе на наличие данных в coursesLiveData. Это необходимо для того чтобы при повороте не данные не закачивались заново (это костыль)
        if (coursesLiveData.value == null) {
            _inProgressLiveData.postValue(true)
            coursesRepo.getCourses {
                inProgressLiveData.mutable().postValue(false)
                coursesLiveData.mutable().postValue(it)
            }
        }
    }

    fun onLessonClick(lessonEntity: LessonEntity) {
        //Вариант 2 (не потоко безопмсно) не желательный вариант
//        selectedLessonsLiveData.value = lessonEntity
        (selectedLessonsLiveData as MutableLiveData).value =
            lessonEntity//Вариант когда агресивно приводим к MutableLiveData
    }

    fun onCourseClick(courseEntity: CourseEntity) {
        //Вариант 1 (потоко безопмсно) предпочтительный вариант
        //postValue работает с многопоточностью, из любого потока делаем postValue и приходит все на главный поток
        selectedCoursesLiveData.mutable().postValue(courseEntity)
    }

    //экстеншен (расширение обычной чужай функции). Можно указать mutable расширение и оно вернет версию MutableLiveData
    //это сделано чтобы случайно во фрагменте случайно не изменить список (в этом рельной безописности нет)
    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as MutableLiveData
    }
}