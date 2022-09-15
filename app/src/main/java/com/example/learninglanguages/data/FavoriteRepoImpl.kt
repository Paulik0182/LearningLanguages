package com.example.learninglanguages.data

import com.example.learninglanguages.domain.entities.FavouriteEntity
import com.example.learninglanguages.domain.repos.FavoriteRepo

class FavoriteRepoImpl : FavoriteRepo {

    private val data: MutableList<FavouriteEntity> = ArrayList()//массив сущьностей

    override fun addFavorite(favoriteEntity: FavouriteEntity) {
        data.add(favoriteEntity)//добавили заметку
    }

    override fun getFavorite(): List<FavouriteEntity> {
        return ArrayList(data)
    }

    init {
        addFavorite(
            FavouriteEntity(
                1
            )
        )
        addFavorite(
            FavouriteEntity(
                2
            )
        )
    }
}