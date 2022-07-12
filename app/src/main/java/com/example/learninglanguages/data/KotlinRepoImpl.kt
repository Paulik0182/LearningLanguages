package com.example.learninglanguages.data

import com.example.learninglanguages.domain.kotlin.KotlinEntity
import com.example.learninglanguages.domain.kotlin.KotlinRepo

class KotlinRepoImpl : KotlinRepo {

    private var data: MutableList<KotlinEntity> = ArrayList()//завели массив сущьностей

    override fun addKotlin(kotlinEntity: KotlinEntity) {
        data.add(kotlinEntity)//добавили заметку
    }

    init {
        addKotlin(
            KotlinEntity(
                1,
                "int",
                "Примитивный тип"
            )
        )
    }
}