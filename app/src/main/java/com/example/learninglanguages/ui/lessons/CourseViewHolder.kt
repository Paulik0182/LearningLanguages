package com.example.learninglanguages.ui.lessons

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.CourseEntity

class CourseViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val titleTextView = itemView.findViewById<TextView>(R.id.title_text_view)
    private lateinit var courseEntity: CourseEntity

    fun bind(courseEntity: CourseEntity) {
        this.courseEntity = courseEntity
        titleTextView.text = courseEntity.name
    }
}