package com.example.learninglanguages.ui.lessons

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.CourseEntity

class CourseViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val lessonsAdapter: LessonsAdapter = LessonsAdapter()

    private val titleTextView = itemView.findViewById<TextView>(R.id.title_text_view)

    //создаем и иницилизируем второй (вложенный) recyclerView (горизонтальный)
    private val recyclerView = itemView.findViewById<RecyclerView>(R.id.lessons_recycler_view)
        .apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = lessonsAdapter
        }
    private lateinit var courseEntity: CourseEntity

    fun bind(courseEntity: CourseEntity) {
        this.courseEntity = courseEntity
        titleTextView.text = courseEntity.name

        //привязываем lessonsAdapter
        lessonsAdapter.setData(courseEntity.lessons)
    }

}