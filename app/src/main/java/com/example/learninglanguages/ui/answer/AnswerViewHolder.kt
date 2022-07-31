package com.example.learninglanguages.ui.answer

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.TaskEntity

class AnswerViewHolder(itemView: View, listener: (TaskEntity) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private val answerTextView = itemView.findViewById<TextView>(R.id.answer_text_view)
    private lateinit var answer: TaskEntity

    fun bind(taskEntity: TaskEntity) {
        answer = taskEntity
        answerTextView.text = taskEntity.rightAnswer
    }

    init {
        //обработка нажатия. при нажатии на элемент происходит вызов listener и передается в него answer который положили в bind
        itemView.setOnClickListener { _ ->
            answer.let {
                listener.invoke(
                    it
                )
            }
        }
    }
}