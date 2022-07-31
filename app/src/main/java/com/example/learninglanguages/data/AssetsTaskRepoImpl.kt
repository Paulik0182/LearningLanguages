package com.example.learninglanguages.data

import android.content.Context
import com.example.learninglanguages.domain.TaskEntity
import com.example.learninglanguages.domain.TaskRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AssetsTaskRepoImpl(
    context: Context,
    fileName: String
) : TaskRepo {

    private var data: List<TaskEntity>

    //в данном случае мы не можем добавлять новые значения в репозиторий
    override fun addTask(taskEntity: TaskEntity) {
        throw IllegalStateException("Нельзя добавлять в репозиторий новые элементы")
    }

    override fun getTasks(): List<TaskEntity> = ArrayList(data)

    init {
        // получаем строку (rawJson). Далее берем Gson для парсинга, также как и в ретрофите
        // и пенаправляем в список данных List<TaskEntity>
        val rawJson: String = context.assets
            .open(fileName)
            .bufferedReader().use {
                it.readText()
            }

        data = Gson().fromJson(rawJson, object : TypeToken<List<TaskEntity>>() {}.type)
    }
}