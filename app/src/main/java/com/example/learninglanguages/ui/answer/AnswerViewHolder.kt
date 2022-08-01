package com.example.learninglanguages.ui.answer

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R

class AnswerViewHolder(itemView: View, listener: (String) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private val answerTextView = itemView.findViewById<TextView>(R.id.answer_text_view)
    private lateinit var answer: String

    fun bind(answer: String) {
        this.answer = answer
        answerTextView.text = answer
    }

    init {
        //обработка нажатия. при нажатии на элемент происходит вызов listener и передается в него answer который положили в bind
        itemView.setOnClickListener { _ ->
            answer.let {
                listener.invoke(it)
            }
        }
    }
}