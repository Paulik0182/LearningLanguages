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
import com.example.learninglanguages.Key
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.CoursesRepo

class LessonFragment : Fragment(), LessonsContract.View {

    private val app: App by lazy { requireActivity().application as App }
    private lateinit var adapter: LessonsAdapter

    private lateinit var lessonsRecyclerView: RecyclerView
    private val coursesRepo: CoursesRepo by lazy {
        app.coursesRepo
    }

    //создаем Presenter (экземпляр проезентора)
    private lateinit var presenter: LessonsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true//флажек для сохранения состаяния экрана
        installingLessonsPresenter()//для того чтобы все время не пересоздавался презентер создание его должно происходить здесь.

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lesson, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        //присоединили view
        presenter.attach(this)//в призентаре вызываем функцию attach и передаем себя

//        initData()
    }

    //отсоединили view
    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    private fun installingLessonsPresenter() {
        val courseId = arguments?.getLong(Key.COURSE_ID_ARGS_KEY)
        presenter = LessonsPresenter(
            coursesRepo,
            courseId
        )//инициализировали презентор и положили в него repo и id
    }

    private fun initData() {
        //Достаем данные
        val courseId = arguments?.getLong(Key.COURSE_ID_ARGS_KEY)
        requireNotNull(courseId)//сваливаем приложение если придет null (не выполнимое условие)
        coursesRepo.getCourse(courseId) {
            adapter.setData(it?.lessons ?: mutableListOf())// пополнение адаптера данными
        }
    }

    private fun initViews(view: View) {

        lessonsRecyclerView = view.findViewById(R.id.lessons_recycler_view)

        //это два параметра которые принимаем на вход. Это слушатель и данные
        lessonsRecyclerView.layoutManager = LinearLayoutManager(context)

        //кэшируем адаптер чтобы его потом вызвать //флажек для разметки
        adapter = LessonsAdapter(
            isFullWidth = true
        ) { lesson ->
            getController().openLesson(lesson)
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

    override fun setCourse(lesson: CourseEntity) {
        adapter.setData(lesson.lessons)// пополнение адаптера данными

//        val courseId = arguments?.getLong(Key.COURSE_ID_ARGS_KEY)
//        requireNotNull(courseId)//сваливаем приложение если придет null (не выполнимое условие)
//        coursesRepo.getCourse(courseId) {
//            adapter.setData(lesson?.lessons ?: emptyList())// пополнение адаптера данными
//        }
    }

    override fun openLesson(lessonEntity: LessonEntity) {
        getController().openLesson(lessonEntity)
    }
}