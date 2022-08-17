package com.example.learninglanguages.ui.courses

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.App
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.CoursesRepo

class CoursesFragment : Fragment(), CoursesContract.View {

    private val app: App by lazy { requireActivity().application as App }
    private lateinit var adapter: CoursesAdapter


    private lateinit var coursesRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val coursesRepo: CoursesRepo by lazy {
        app.coursesRepo
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_courses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initData()

    }

    private fun initData() {
        showProgress(true)
        // кулбе
        coursesRepo.getCourses {
            showProgress(false)
            adapter.setData(it)// пополнение адаптера данными
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
                getController().openLesson(it)
            },
            onShowAllListener = {
                getController().openCourse(it)
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

    override fun showProgress(inProgress: Boolean) {
        coursesRecyclerView.isVisible = !inProgress
        progressBar.isVisible = inProgress
    }

    override fun setCourses(course: List<CourseEntity>) {
        TODO("Not yet implemented")
    }

    override fun openLesson(lessonEntity: LessonEntity) {
        TODO("Not yet implemented")
    }

    override fun openCourse(courseEntity: CourseEntity) {
        TODO("Not yet implemented")
    }
}