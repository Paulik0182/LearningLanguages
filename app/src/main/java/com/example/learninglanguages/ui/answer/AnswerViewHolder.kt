package com.example.learninglanguages.ui.answer

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.TaskEntity

class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val answerTextView = itemView.findViewById<TextView>(R.id.answer_text_view)

    fun bind(taskEntity: TaskEntity) {
        answerTextView.text = taskEntity.variantsAnswer.randomOrNull()
    }

}