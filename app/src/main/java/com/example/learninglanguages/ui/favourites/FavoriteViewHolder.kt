package com.example.learninglanguages.ui.favourites

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.domain.entities.FavouriteLessonEntity

class FavoriteViewHolder(itemView: View, listener: (FavouriteLessonEntity) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private lateinit var favourite: FavouriteLessonEntity

    fun bind(favouriteEntity: FavouriteLessonEntity) {
        this.favourite = favouriteEntity
    }

    init {
        itemView.setOnClickListener {
            listener.invoke(favourite)
        }
    }
}