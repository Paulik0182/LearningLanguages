package com.example.learninglanguages.domain.repos

import com.example.learninglanguages.domain.entities.TaskEntity

interface TaskRepo {
    fun addTask(taskEntity: TaskEntity)
    fun getTasks(): List<TaskEntity>
}