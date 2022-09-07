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
    private val courseId: Long,
    private val lessonId: Long
) : ViewModel() {

    //Сделали класс Factory (это объект Фабрика) в которую кладем внутрь модели
    class Factory(
        private val coursesRepo: CoursesRepo,
        private val courseId: Long,
        private val lessonId: Long
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TaskViewModel(coursesRepo, courseId, lessonId) as T
        }
    }

    //одно из решений над Mutable (это стандартно принятый этот метод)
    private val _inProgressLiveData: MutableLiveData<Boolean> = MutableLiveData()

    // сразу когда чтото будет кластся в inProgressLiveData, сразу все подписчики будут получать изменения
    val inProgressLiveData: LiveData<Boolean> = _inProgressLiveData

    val tasksLiveData: LiveData<TaskEntity> = MutableLiveData()
    private var tasks: MutableList<TaskEntity> = mutableListOf()

    val selectedSuccessLiveData: LiveData<Unit> = SingleLiveEvent()
    val wrongAnswerLiveData: LiveData<Unit> = SingleLiveEvent()

    init {
        if (tasksLiveData.value == null) {
            _inProgressLiveData.postValue(true)
            coursesRepo.getLesson(courseId, lessonId) {
                it?.let {
                    inProgressLiveData.mutable().postValue(false)
                    tasks = it.tasks//сохраняем список на старте запуска
                    tasksLiveData.mutable().postValue(getNextTask())
                }
            }
        }
    }

    fun onAnswerSelect(userAnswer: String) {
        if (checkingAnswer(userAnswer, tasksLiveData.value!!.rightAnswer)) {
            val taskEntity = getNextTask()
            if (taskEntity == null) {
                selectedSuccessLiveData.mutable().postValue(Unit)
            } else {
                tasksLiveData.mutable().postValue(taskEntity)
            }
        } else {
            wrongAnswerLiveData.mutable().postValue(Unit)
        }
    }

    private fun checkingAnswer(userAnswer: String, rightAnswer: String): Boolean {
        return rightAnswer == userAnswer
    }

    private fun getNextTask(): TaskEntity? {
        val nextTask = tasks.randomOrNull()// означает, что рандом может принять null
        tasks.remove(nextTask)//удаляем из списка одно задание
        return nextTask
    }

    //экстеншен (расширение обычной чужай функции). Можно указать mutable расширение и оно вернет версию MutableLiveData
    //это сделано чтобы случайно во фрагменте случайно не изменить список (в этом рельной безописности нет)
    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as MutableLiveData
    }
}