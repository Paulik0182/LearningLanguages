package com.example.learninglanguages.data

import com.example.learninglanguages.domain.TaskEntity
import com.example.learninglanguages.domain.TaskRepo

class KotlinTaskRepoImpl : TaskRepo {

    private val data: MutableList<TaskEntity> = ArrayList()//завели массив сущьностей

    override fun addTask(taskEntity: TaskEntity) {
        data.add(taskEntity)//добавили заметку
    }

    override fun getTasks(): List<TaskEntity> {
        return ArrayList(data)
    }

    init {
        addTask(
            TaskEntity(
                1,
                "Что такое object в Kotlin?",
                listOf("Синглтон", "Базовый класс Java", "Поток", "Переменная"),
                "Синглтон"
            )
        )
        addTask(
            TaskEntity(
                2,
                "Проверка на равенство позволяющая сравнивать данные, содержащиеся в переменных",
                listOf("toString", "equals", "true", "argument"),
                "equals"
            )
        )
    }
}