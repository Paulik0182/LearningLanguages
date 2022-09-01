package com.example.learninglanguages.ui.task

import com.example.learninglanguages.domain.entities.TaskEntity

interface TaskContract {

    //это для управления view
    interface View {
        fun showProgress(inProgress: Boolean)
        fun setTask(taskEntity: TaskEntity)
        fun openSuccessScreen()//открываем финишный фрагмент
        fun showNotice(notice: String)
    }

    // это для того чтобы была возможность сообщить view о происходящем (некая логика)
    interface Presenter {
        fun onAnswerClick()// нажали и передали задание
    }
}
