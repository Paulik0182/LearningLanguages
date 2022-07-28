package com.example.learninglanguages.ui.answer

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.TaskEntity

class AnswerAdapter(
    private var data: List<TaskEntity> //кэшируем сущность
) : RecyclerView.Adapter<AnswerViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(answer: List<TaskEntity>) {
        data = answer
        notifyDataSetChanged()//для обнавления
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_answer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(pos: Int): TaskEntity = data[pos]

    override fun getItemCount(): Int = data.size
}