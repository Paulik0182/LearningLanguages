package com.example.learninglanguages.ui.favourites

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.FavouriteLessonEntity

class FavoritesAdapter(
    private var data: MutableList<FavouriteLessonEntity> = mutableListOf(),
    private var listener: (FavouriteLessonEntity) -> Unit = {}
) : RecyclerView.Adapter<FavoriteViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(favorite: MutableList<FavouriteLessonEntity>) {
        data = favorite
        notifyDataSetChanged()//обнавили данные
    }

    fun setOnItemClickListener(listener: (FavouriteLessonEntity) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_lesson_full_width, parent, false), listener
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): FavouriteLessonEntity = data[position]

    override fun getItemCount(): Int = data.size

}