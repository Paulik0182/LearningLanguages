package com.example.learninglanguages.domain

interface TaskRepo {
    fun addTask(taskEntity: TaskEntity)
    fun getTasks(): List<TaskEntity>
}