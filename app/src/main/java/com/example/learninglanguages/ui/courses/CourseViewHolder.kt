package com.example.learninglanguages.ui.courses

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.ui.lessons.LessonsAdapter

class CourseViewHolder(
    itemView: View,
    onLessonClick: (LessonEntity) -> Unit,
    onShowAllClick: (CourseEntity) -> Unit
) :
    RecyclerView.ViewHolder(itemView) {

    private val lessonsAdapter: LessonsAdapter = LessonsAdapter {
        //здесь нам должно быть сообщено id курса
        onLessonClick.invoke(it)
    }

    private val titleTextView = itemView.findViewById<TextView>(R.id.title_text_view)
    private val showAllTextView = itemView.findViewById<TextView>(R.id.show_all_text_view)

    //создаем и иницилизируем второй (вложенный) recyclerView (горизонтальный)
    private val recyclerView = itemView.findViewById<RecyclerView>(R.id.lessons_recycler_view)
        .apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = lessonsAdapter

            //для того чтобы во view скрол элементы останавливались по середине экрана
            val helper: SnapHelper = LinearSnapHelper()
            helper.attachToRecyclerView(this)
        }

    private lateinit var courseEntity: CourseEntity


    fun bind(courseEntity: CourseEntity) {
        this.courseEntity = courseEntity
        titleTextView.text = courseEntity.name

        //привязываем lessonsAdapter
        lessonsAdapter.setData(courseEntity.lessons)
    }

    init {
        showAllTextView.setOnClickListener {
            onShowAllClick.invoke(courseEntity)
        }
    }
}