package com.example.learninglanguages.ui.lessons

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.App
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.LessonRepo

class LessonsFragment : Fragment() {

    private val app: App by lazy { requireActivity().application as App }

    private lateinit var lessonsRecyclerView: RecyclerView
    private val lessonsRepo: LessonRepo by lazy {
        app.lessonRepo
    }

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

    }

    private fun initViews() {
        //можжно сделать такую комбинацию для инициализации переменной, чтобы в каждой строке не
        // добавлять view и не передовать view дополнительно
        //это значит, если view существует то выполнить следующее (apply это где аргумент
        // передается this. это такая комбинация где вместо this подставляется то что слево от apply)
        view?.apply {
            lessonsRecyclerView = findViewById(R.id.lessons_recycler_view)
        }

        //это два параметра которые принимаем на вход. Это слушатель и данные
        lessonsRecyclerView.layoutManager = LinearLayoutManager(context)
        lessonsRecyclerView.adapter = LessonsAdapter(lessonsRepo.getLessons()) {
            getController().openLesson(it)

        }
    }

    private fun getController(): Controller = activity as Controller

    interface Controller {
        fun openLesson(lessonEntity: LessonEntity)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()  //Вариант 2. агресивный способ проверки наличия контроллера. Если нет контроллера, приложение свалтится на присоединение к фрагмента к активити
    }
}