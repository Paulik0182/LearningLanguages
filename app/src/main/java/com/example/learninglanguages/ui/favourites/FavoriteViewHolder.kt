package com.example.learninglanguages.ui.favourites

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.domain.entities.FavouriteEntity

class FavoriteViewHolder(itemView: View, listener: (FavouriteEntity) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private lateinit var favourite: FavouriteEntity

    fun bind(favouriteEntity: FavouriteEntity) {
        this.favourite = favouriteEntity
    }

    init {
        itemView.setOnClickListener {
            listener.invoke(favourite)
        }
    }
}