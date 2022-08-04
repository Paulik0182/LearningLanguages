package com.example.learninglanguages.ui.lessons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.LessonEntity

class LessonsAdapter(
    //адаптер принимает на вход данные и слушатель
    private var data: List<LessonEntity> = emptyList(),
    private var listener: (LessonEntity) -> Unit = {}
) : RecyclerView.Adapter<LessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_lesson, parent, false), listener
        )
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(pos: Int): LessonEntity = data[pos]

    override fun getItemCount(): Int = data.size
}