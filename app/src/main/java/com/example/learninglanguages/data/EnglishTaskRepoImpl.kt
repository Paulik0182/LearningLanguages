package com.example.learninglanguages.data

import com.example.learninglanguages.domain.TaskEntity
import com.example.learninglanguages.domain.TaskRepo

class EnglishTaskRepoImpl : TaskRepo {

    private val data: MutableList<TaskEntity> = ArrayList()//завели массив сущьностей

    override fun addTask(taskEntity: TaskEntity) {
        data.add(taskEntity)//добавили заметку
    }

    override fun getTask(): List<TaskEntity> {
        return ArrayList(data)
    }

    init {
        addTask(
            TaskEntity(
                1,
                "Good afternoon",
                listOf("Добрый день", "Привет", "Добрый вечер", "До свидания"),
                0
            )
        )
        addTask(
            TaskEntity(
                2,
                "Table",
                listOf("Стул", "Стол", "Ручка", "Таблица"),
                1
            )
        )
        addTask(
            TaskEntity(
                3,
                "Sofa",
                listOf("Стол", "Кресло", "Диван", "Ваза"),
                2
            )
        )
        addTask(
            TaskEntity(
                4,
                "Chair",
                listOf("Стул", "Диван", "Ваза", "Ручка"),
                0
            )
        )
        addTask(
            TaskEntity(
                5,
                "Pencil",
                listOf("Ручка", "Линейка", "Пенал", "Карандаш"),
                3
            )
        )
        addTask(
            TaskEntity(
                1,
                "Telephone",
                listOf("Телега", "Телефон", "Ручка", "Телевизор"),
                1
            )
        )
    }
}