package com.example.learninglanguages.domain

/**
 * Шина событий.
 * Шину событий также как и репозиторий везде можно прокидывать, либо рядом,
 * либо объединить в одну сущьность.
 * вместо того чтобы пользоватся Репозиторием (FavoriteRepo), завести interactor и в нем
 * объединить как отправку событий через репозиторий событить так и инвенты
 */

import androidx.lifecycle.LiveData

interface FavoriteEventBus {
    val events: LiveData<Pair<Long, Boolean>>
    fun postEvent(id: Long, isFavorite: Boolean)
}