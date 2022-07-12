package com.example.learninglanguages.data

import com.example.learninglanguages.domain.english.EnglishEntity
import com.example.learninglanguages.domain.english.EnglishRepo

class EnglishRepoImpl : EnglishRepo {

    private var data: MutableList<EnglishEntity> = ArrayList()//завели массив сущьностей

    override fun addEnglish(englishEntity: EnglishEntity) {
        data.add(englishEntity)//добавили заметку
    }

    init {
        addEnglish(
            EnglishEntity(
                1,
                "Good afternoon",
                "Добрый день"
            )
        )
        addEnglish(
            EnglishEntity(
                2,
                "Table",
                "Стол"
            )
        )
        addEnglish(
            EnglishEntity(
                3,
                "Sofa",
                "Диван"
            )
        )
        addEnglish(
            EnglishEntity(
                4,
                "Chair",
                "Стул"
            )
        )
        addEnglish(
            EnglishEntity(
                5,
                "Pencil",
                "Карандаш"
            )
        )
        addEnglish(
            EnglishEntity(
                6,
                "Telephone",
                "Телефон"
            )
        )
    }
}