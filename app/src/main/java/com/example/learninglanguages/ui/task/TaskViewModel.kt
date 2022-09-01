package com.example.learninglanguages.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learninglanguages.domain.entities.TaskEntity
import com.example.learninglanguages.domain.repos.CoursesRepo
import com.example.learninglanguages.utils.SingleLiveEvent

class TaskViewModel(
    private val coursesRepo: CoursesRepo,
    private val taskList: MutableList<TaskEntity>,
    private val answer: Boolean,
    private val courseId: Long,
    private val lessonId: Long
) : ViewModel() {

    //Сделали класс Factory (это объект Фабрика) в которую кладем внутрь модели
    class Factory(
        private val coursesRepo: CoursesRepo,
        private val taskList: MutableList<TaskEntity>,
        private val answer: Boolean,
        private val courseId: Long,
        private val lessonId: Long
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TaskViewModel(coursesRepo, taskList, answer, courseId, lessonId) as T
        }
    }

    //одно из решений над Mutable (это стандартно принятый этот метод)
    private val _inProgressLiveData: MutableLiveData<Boolean> = MutableLiveData()

    // сразу когда чтото будет кластся в inProgressLiveData, сразу все подписчики будут получать изменения
    val inProgressLiveData: LiveData<Boolean> = _inProgressLiveData

    val tasksLiveData: LiveData<TaskEntity> = MutableLiveData()

    val selectedSuccessLiveData: LiveData<TaskEntity> = SingleLiveEvent()

    init {
        if (tasksLiveData.value == null) {
            _inProgressLiveData.postValue(true)
            coursesRepo.getLesson(courseId, lessonId) {
                it?.let {
                    inProgressLiveData.mutable().postValue(false)
                    tasksLiveData.mutable().postValue(it)
                }
            }
        }
    }

    private fun handleAnswerClick() {
        if (answer) {
            val taskEntity = getNextTask()
            if (taskEntity == null) {
                selectedSuccessLiveData
            } else {
                tasksLiveData
            }
        } else {
            //todo написать Тост, что вместо контекста подставлять.
        }
    }

    private fun getNextTask(): TaskEntity? {
        val nextTask = taskList.randomOrNull()// означает, что рандом может принять null
        taskList.remove(nextTask)//удаляем из списка одно задание
        return nextTask
    }

    //экстеншен (расширение обычной чужай функции). Можно указать mutable расширение и оно вернет версию MutableLiveData
    //это сделано чтобы случайно во фрагменте случайно не изменить список (в этом рельной безописности нет)
    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as MutableLiveData
    }
}