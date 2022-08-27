package com.example.learninglanguages.ui.task.answer

import com.example.learninglanguages.domain.entities.TaskEntity

interface TasksContract {

    //это для управления view
    interface View {
        fun showProgress(inProgress: Boolean)
        fun setTask(tasks: TaskEntity)// данные (показать)
        fun openSuccessScreen()//открываем финишный фрагмент
    }

    // это для того чтобы была возможность сообщить view о происходящем (некая логика)
    interface Presenter {
        fun attach(view: View)//присоединить view
        fun detach()//отсоединить, уничтожить view

        fun onTaskClick()// нажали и передали задание

    }
}