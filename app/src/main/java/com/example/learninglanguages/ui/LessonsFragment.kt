package com.example.learninglanguages.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.ThemeTask

class LessonsFragment : Fragment() {

    private lateinit var englishCv1: CardView
    private lateinit var englishCv2: CardView
    private lateinit var kotlinCv1: CardView
    private lateinit var kotlinCv2: CardView
    private lateinit var javaCv1: CardView
    private lateinit var javaCv2: CardView
    private lateinit var nameCv1: CardView
    private lateinit var nameCv2: CardView

    private lateinit var englishIv1: ImageView
    private lateinit var englishIv2: ImageView
    private lateinit var kotlinIv1: ImageView
    private lateinit var kotlinIv2: ImageView
    private lateinit var javaIv1: ImageView
    private lateinit var javaIv2: ImageView
    private lateinit var nameIv1: ImageView
    private lateinit var nameIv2: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lessons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        generateImageView()

        englishCv1.setOnClickListener {
            getController().openLesson(ThemeTask.ENGLISH, R.string.english_level_1)
        }

        kotlinCv1.setOnClickListener {
            getController().openLesson(ThemeTask.KOTLIN, R.string.kotlin_level_1)
        }
    }

    private fun initViews() {
        //можжно сделать такую комбинацию для инициализации переменной, чтобы в каждой строке не
        // добавлять view и не передовать view дополнительно
        //это значит, если view существует то выполнить следующее (apply это где аргумент
        // передается this. это такая комбинация где вместо this подставляется то что слево от apply)
        view?.apply {
            englishCv1 = findViewById(R.id.english1_card_view)
            englishCv2 = findViewById(R.id.english2_card_view)
            englishIv1 = findViewById(R.id.english1_image_view)
            englishIv2 = findViewById(R.id.english2_image_view)

            kotlinCv1 = findViewById(R.id.kotlin1_card_view)
            kotlinCv2 = findViewById(R.id.kotlin2_card_view)
            kotlinIv1 = findViewById(R.id.kotlin1_image_view)
            kotlinIv2 = findViewById(R.id.kotlin2_image_view)

            javaCv1 = findViewById(R.id.java1_card_view)
            javaCv2 = findViewById(R.id.java2_card_view)
            javaIv1 = findViewById(R.id.java1_image_view)
            javaIv2 = findViewById(R.id.java2_image_view)

            nameCv1 = findViewById(R.id.name1_card_view)
            nameCv2 = findViewById(R.id.name2_card_view)
            nameIv1 = findViewById(R.id.name1_image_view)
            nameIv2 = findViewById(R.id.name2_image_view)
        }
    }

    private fun generateImageView() {

        englishIv1.setImageResource(R.drawable.english_book)
        englishIv2.setImageResource(R.drawable.ehglish_anime)

        kotlinIv1.setImageResource(R.drawable.kotlin_tel)
        kotlinIv2.setImageResource(R.drawable.kotlin)

        javaIv1.setImageResource(R.drawable.java_logotip_1)
        javaIv2.setImageResource(R.drawable.java_logotip_2)

        nameIv1.setImageResource(R.drawable.no_image)
        nameIv2.setImageResource(R.drawable.no_name)

    }

    private fun getController(): Controller = activity as Controller

    interface Controller {
        fun openLesson(themeTask: ThemeTask, stringResId: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()  //Вариант 2. агресивный способ проверки наличия контроллера. Если нет контроллера, приложение свалтится на присоединение к фрагмента к активити
    }
}