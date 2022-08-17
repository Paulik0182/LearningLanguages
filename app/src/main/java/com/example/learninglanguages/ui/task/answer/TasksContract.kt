package com.example.learninglanguages.ui.task.answer

import com.example.learninglanguages.domain.entities.TaskEntity

interface TasksContract {

    //это для управления view
    interface View {
        fun showProgress(inProgress: Boolean)
        fun setCourses(tasks: List<TaskEntity>)// данные (показать)
//        fun openLesson(lessonEntity: LessonEntity)//открыть урок

    }

    // это для того чтобы была возможность сообщить view о происходящем (некая логика)
    interface Presenter {
        fun attach(view: View)//присоединить view
        fun detach()//отсоединить, уничтожить view

        fun onTaskClick(taskEntity: TaskEntity)// нажали и передали урок

    }
}