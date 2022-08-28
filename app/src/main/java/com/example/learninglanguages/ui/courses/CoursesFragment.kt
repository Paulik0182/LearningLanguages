package com.example.learninglanguages.ui.courses

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoursesFragment : Fragment(R.layout.fragment_courses) {

    private val viewModel: CoursesViewModel by viewModel()

    private lateinit var adapter: CoursesAdapter

    private lateinit var coursesRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        //observe - это наблюдатель
        // подписываемся на inProgressLiveData
        viewModel.inProgressLiveData.observe(viewLifecycleOwner) { inProgress ->
            //сюда приходит значение
            coursesRecyclerView.isVisible = !inProgress
            progressBar.isVisible = inProgress
        }

        viewModel.coursesLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)// пополнение адаптера данными
        }

        viewModel.selectedLessonsLiveData.observe(viewLifecycleOwner) {
            getController().openLesson(it)
        }

        viewModel.selectedCoursesLiveData.observe(viewLifecycleOwner) { courseEntity ->
            getController().openCourse(courseEntity)
        }
    }

    private fun initViews() {
        //можжно сделать такую комбинацию для инициализации переменной, чтобы в каждой строке не
        // добавлять view и не передовать view дополнительно
        //это значит, если view существует то выполнить следующее (apply это где аргумент
        // передается this. это такая комбинация где вместо this подставляется то что слево от apply)
        view?.apply {
            coursesRecyclerView = findViewById(R.id.courses_recycler_view)
            progressBar = findViewById(R.id.progress_courses_bar)
        }

        //это два параметра которые принимаем на вход. Это слушатель и данные
        coursesRecyclerView.layoutManager = LinearLayoutManager(context)

        //кэшируем адаптер чтобы его потом вызвать
        adapter = CoursesAdapter(
            onLessonClickListener = {
                viewModel.onLessonClick(it)
            },
            onShowAllListener = {
                viewModel.onCourseClick(it)
            })
        coursesRecyclerView.adapter = adapter
    }

    private fun getController(): Controller = activity as Controller

    interface Controller {
        fun openLesson(lessonEntity: LessonEntity)
        fun openCourse(courseEntity: CourseEntity)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()  //Вариант 2. агресивный способ проверки наличия контроллера. Если нет контроллера, приложение свалтится на присоединение к фрагмента к активити
    }
}