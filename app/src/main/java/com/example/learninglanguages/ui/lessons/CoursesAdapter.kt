package com.example.learninglanguages.ui.lessons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity

class CoursesAdapter(
    //адаптер принимает на вход данные
    private var data: List<CourseEntity> = emptyList(),
    private var listener: (CourseEntity) -> Unit = {},
    private var onLessonClickListener: (LessonEntity) -> Unit = {}
) : RecyclerView.Adapter<CourseViewHolder>() {

    fun setData(newData: List<CourseEntity>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_course, parent, false),
            onLessonClickListener,
            listener
        )
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(pos: Int): CourseEntity = data[pos]

    override fun getItemCount(): Int = data.size
}