package com.example.learninglanguages.domain

interface TaskRepo {
    fun addTask(taskEntity: TaskEntity)
    fun getTask(): List<TaskEntity>
}