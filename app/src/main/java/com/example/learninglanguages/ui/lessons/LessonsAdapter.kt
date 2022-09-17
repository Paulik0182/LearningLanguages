package com.example.learninglanguages.ui.lessons

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.FavouriteLessonEntity

class LessonsAdapter(
    //адаптер принимает на вход данные и слушатель
    private val isFullWidth: Boolean = false,//флажек для переключением между элементами на экране
    private var data: List<FavouriteLessonEntity> = mutableListOf(),
    private var courseId: Long = -1,
    private var listener: (Long, FavouriteLessonEntity) -> Unit = { _, _ -> },
) : RecyclerView.Adapter<LessonViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(courseId: Long, newData: List<FavouriteLessonEntity>) {
        data = newData
        this.courseId = courseId
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(
            LayoutInflater.from(parent.context).inflate(
                //условие выбора загрузки фрагмента
                if (isFullWidth) R.layout.item_lesson_full_width
                else
                    R.layout.item_lesson, parent, false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(courseId, getItem(position))
    }

    private fun getItem(pos: Int): FavouriteLessonEntity = data[pos]

    override fun getItemCount(): Int = data.size
}