package com.example.learninglanguages.ui.favorites

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.domain.entities.LessonIdEntity

class FavoriteViewHolder(itemView: View, listener: (LessonIdEntity) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private lateinit var favourite: LessonIdEntity

    fun bind(favouriteEntity: LessonIdEntity) {
        this.favourite = favouriteEntity
    }

    init {
        itemView.setOnClickListener {
            listener.invoke(favourite)
        }
    }
}