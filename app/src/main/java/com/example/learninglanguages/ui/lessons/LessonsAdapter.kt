package com.example.learninglanguages.ui.lessons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.LessonEntity

class LessonsAdapter(
    //адаптер принимает на вход данные и слушатель
    private val isFullWidth: Boolean = false,//флажек для переключением между элементами на экране
    private var data: List<LessonEntity> = mutableListOf(),
    private var listener: (LessonEntity) -> Unit = {},
) : RecyclerView.Adapter<LessonViewHolder>() {

    fun setData(newData: List<LessonEntity>) {
        data = newData
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
        holder.bind(getItem(position))
    }

    private fun getItem(pos: Int): LessonEntity = data[pos]

    override fun getItemCount(): Int = data.size
}