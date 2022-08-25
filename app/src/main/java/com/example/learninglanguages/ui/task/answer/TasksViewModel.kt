package com.example.learninglanguages.ui.task.answer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learninglanguages.domain.entities.TaskEntity
import com.example.learninglanguages.domain.repos.CoursesRepo
import com.example.learninglanguages.utils.SingleLiveEvent

class TasksViewModel(
    private val coursesRepo: CoursesRepo
) : ViewModel() {

    //Сделали класс Factory (это объект Фабрика) в которую кладем внутрь модели
    class Factory(private val coursesRepo: CoursesRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TasksViewModel(coursesRepo) as T
        }
    }

    //одно из решений над Mutable (это стандартно принятый этот метод)
    private val _inProgressLiveData: MutableLiveData<Boolean> = MutableLiveData()

    // сразу когда чтото будет кластся в inProgressLiveData, сразу все подписчики будут получать изменения
    val inProgressLiveData: LiveData<Boolean> = _inProgressLiveData

    val tasksLiveData: LiveData<TaskEntity> = MutableLiveData()// данные (показать)
    val selectedSuccessLiveData: LiveData<TaskEntity> =
        SingleLiveEvent()//открываем финишный фрагмент

    init {
        if (tasksLiveData.value == null) {
            _inProgressLiveData.postValue(false)
            coursesRepo.getCourses {
                inProgressLiveData.mutable().postValue(false)
            }
        }
    }

    //экстеншен (расширение обычной чужай функции). Можно указать mutable расширение и оно вернет версию MutableLiveData
    //это сделано чтобы случайно во фрагменте случайно не изменить список (в этом рельной безописности нет)
    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as MutableLiveData
    }
}