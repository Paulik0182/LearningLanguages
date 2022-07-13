package com.example.learninglanguages.ui.english

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.learninglanguages.App
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.english.EnglishRepo

class EnglishFragment : Fragment() {

    private lateinit var taskEnglishTv: TextView
    private lateinit var answerEnglishTv1: TextView
    private lateinit var answerEnglishTv2: TextView
    private lateinit var answerEnglishTv3: TextView
    private lateinit var answerEnglishTv4: TextView

    private val add: App by lazy { requireActivity().application as App }

    private lateinit var englishRepo: EnglishRepo//экземпляр класса, для того чтобы получить данные

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_english, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        englishRepo = add.englishRepo//достали из репо application (class App)

    }

    private fun initViews(view: View) {

        taskEnglishTv = view.findViewById(R.id.task_english_text_view)
        answerEnglishTv1 = view.findViewById(R.id.answer1_english_text_view)
        answerEnglishTv2 = view.findViewById(R.id.answer2_english_text_view)
        answerEnglishTv3 = view.findViewById(R.id.answer3_english_text_view)
        answerEnglishTv4 = view.findViewById(R.id.answer4_english_text_view)

    }
}