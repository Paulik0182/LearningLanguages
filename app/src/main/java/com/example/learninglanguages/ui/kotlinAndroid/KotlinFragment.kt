package com.example.learninglanguages.ui.kotlinAndroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.learninglanguages.App
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.kotlin.KotlinRepo

class KotlinFragment : Fragment() {

    private lateinit var taskKotlinTv: TextureView
    private lateinit var answerKotlinTv1: TextView
    private lateinit var answerKotlinTv2: TextView
    private lateinit var answerKotlinTv3: TextView
    private lateinit var answerKotlinTv4: TextView

    private val add: App by lazy { requireActivity().application as App }

    private lateinit var kotlinRepo: KotlinRepo//экземпляр класса, для того чтобы получить данные

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kotlin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        kotlinRepo = add.kotlinRepo//достали из репо application (class App)

    }

    private fun initViews(view: View) {

        taskKotlinTv = view.findViewById(R.id.task_kotlin_text_view)
        answerKotlinTv1 = view.findViewById(R.id.answer1_kotlin_text_view)
        answerKotlinTv2 = view.findViewById(R.id.answer2_kotlin_text_view)
        answerKotlinTv3 = view.findViewById(R.id.answer3_kotlin_text_view)
        answerKotlinTv4 = view.findViewById(R.id.answer4_kotlin_text_view)

    }
}