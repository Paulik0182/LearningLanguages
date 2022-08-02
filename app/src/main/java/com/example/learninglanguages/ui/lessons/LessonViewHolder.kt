package com.example.learninglanguages.ui.lessons

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.LessonEntity

class LessonViewHolder(itemView: View, listener: (LessonEntity) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private val titleTextView = itemView.findViewById<TextView>(R.id.title_text_view)
    private val coverImageView = itemView.findViewById<ImageView>(R.id.cover_image_view)
    private lateinit var lessonEntity: LessonEntity

    fun bind(lessonEntity: LessonEntity) {
        this.lessonEntity = lessonEntity
        titleTextView.text = lessonEntity.name
        coverImageView.load(lessonEntity.imageUrl)
    }

    init {
        itemView.setOnClickListener {
            listener.invoke(lessonEntity)
        }
    }

}