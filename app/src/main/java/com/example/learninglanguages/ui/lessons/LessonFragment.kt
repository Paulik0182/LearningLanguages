package com.example.learninglanguages.ui.lessons

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.App
import com.example.learninglanguages.Key
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.LessonEntity

class LessonFragment : Fragment(R.layout.fragment_lesson) {

    private val app: App by lazy { requireActivity().application as App }
    private lateinit var adapter: LessonsAdapter
    private lateinit var progressBar: ProgressBar
    private val viewModel: LessonsViewModel by viewModels {
        LessonsViewModel.Factory(app.coursesRepo, fragmentUid.toLong())
    }

    private lateinit var lessonsRecyclerView: RecyclerView

    private lateinit var fragmentUid: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        fragmentUid = arguments?.getLong(Key.COURSE_ID_ARGS_KEY).toString()

        //observe - это наблюдатель
        // подписываемся на inProgressLiveData
        viewModel.inProgressLiveData.observe(viewLifecycleOwner) { inProgress ->
            //сюда приходит значение
            lessonsRecyclerView.isVisible = !inProgress
            progressBar.isVisible = inProgress
        }

        viewModel.coursesLiveData.observe(viewLifecycleOwner) {
            it?.let { adapter.setData(it.lessons) }// пополнение адаптера данными
        }

        viewModel.selectedLessonsLiveData.observe(viewLifecycleOwner) {
            getController().openLesson(it)
        }

        view.findViewById<TextView>(R.id.fragment_id_text_view).text = fragmentUid.toString()
        Toast.makeText(context, fragmentUid.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun initViews(view: View) {

        lessonsRecyclerView = view.findViewById(R.id.lessons_recycler_view)
        progressBar = view.findViewById(R.id.lessons_progress_courses_bar)

        //это два параметра которые принимаем на вход. Это слушатель и данные
        lessonsRecyclerView.layoutManager = LinearLayoutManager(context)

        //кэшируем адаптер чтобы его потом вызвать //флажек для разметки
        adapter = LessonsAdapter(
            isFullWidth = true
        ) { lesson ->
            viewModel.onLessonClick(lesson)
        }
        lessonsRecyclerView.adapter = adapter
    }

    private fun getController(): Controller = activity as Controller

    interface Controller {
        fun openLesson(lessonEntity: LessonEntity)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()  //Вариант 2. агресивный способ проверки наличия контроллера. Если нет контроллера, приложение свалтится на присоединение к фрагмента к активити
    }

    companion object {
        @JvmStatic
        fun newInstance(courseId: Long) = LessonFragment().apply {
            arguments = Bundle().apply {
                putLong(Key.COURSE_ID_ARGS_KEY, courseId)
            }
        }
    }
}