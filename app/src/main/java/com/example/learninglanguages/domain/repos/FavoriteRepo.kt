package com.example.learninglanguages.domain.repos

import com.example.learninglanguages.domain.entities.FavouriteEntity

interface FavoriteRepo {
    fun addFavorite(favoriteEntity: FavouriteEntity)
    fun getFavorite(): List<FavouriteEntity>
}