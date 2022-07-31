package com.example.learninglanguages.data

import com.example.learninglanguages.domain.TaskEntity
import com.example.learninglanguages.domain.TaskRepo

class EnglishTaskRepoImpl : TaskRepo {

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
                "Good afternoon",
                listOf("Добрый день", "Привет", "Добрый вечер", "До свидания"),
                "Добрый день",
                1
            )
        )
        addTask(
            TaskEntity(
                2,
                "Table",
                listOf("Стул", "Стол", "Ручка", "Таблица"),
                "Стол",
                1
            )
        )
        addTask(
            TaskEntity(
                3,
                "Sofa",
                listOf("Стол", "Кресло", "Диван", "Ваза"),
                "Диван",
                1
            )
        )
        addTask(
            TaskEntity(
                4,
                "Chair",
                listOf("Стул", "Диван", "Ваза", "Ручка"),
                "Стул",
                2
            )
        )
        addTask(
            TaskEntity(
                5,
                "Pencil",
                listOf("Ручка", "Линейка", "Пенал", "Карандаш"),
                "Карандаш",
                2
            )
        )
        addTask(
            TaskEntity(
                6,
                "Telephone",
                listOf("Телега", "Телефон", "Ручка", "Телевизор"),
                "Телефон",
                2
            )
        )
        addTask(
            TaskEntity(
                7,
                "Mouse",
                listOf("Мышь", "Собака", "Скунс", "Змея"),
                "Мышь",
                2
            )
        )
    }
}