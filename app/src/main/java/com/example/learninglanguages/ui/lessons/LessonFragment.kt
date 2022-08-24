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
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity

class LessonFragment : Fragment(R.layout.fragment_lesson) {

    private val app: App by lazy { requireActivity().application as App }
    private lateinit var adapter: LessonsAdapter
    private lateinit var progressBar: ProgressBar
    private val viewModel: LessonsViewModel by viewModels()

//    private val presenter: LessonsContract.Presenter by lazy { extractPresenter() }//поздняя инициализация презентора, положили в него repo
    //в связи с тем что презентер при каждом повороте пересоздается, а это если необходимо сохранять экран, необходимо презентор сохранить вне данного класса

    //этот метод достает из MAP или создает новый презентер
//    private fun extractPresenter(): LessonsContract.Presenter {
//        val presenter = app.rotationLessonFreeStorage[fragmentUid] as LessonsContract.Presenter?
//            ?: LessonsViewModel(
//                coursesRepo,
//                requireNotNull(arguments?.getLong(Key.COURSE_ID_ARGS_KEY))
//            )
//        app.rotationLessonFreeStorage[fragmentUid] = presenter
//        return presenter
//    }

    private lateinit var lessonsRecyclerView: RecyclerView
    private val fragmentUid: Long? = arguments?.getLong(Key.COURSE_ID_ARGS_KEY)
    // TODO: Проблема передачи ID (как передать ID в LessonViewModel
    //уникальный id (для того чтобы можно было сохранить состояние экрана за пределами класса
//    private lateinit var fragmentUid: String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        fragmentUid =
//            savedInstanceState?.getString(Key.FRAGMENT_LESSON_UUID_KEY) ?: UUID.randomUUID()
//                .toString()
//    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        //при сохроанении положить ID
//        outState.putString(Key.FRAGMENT_LESSON_UUID_KEY, fragmentUid)
//    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_lesson, container, false)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        viewModel.setLessonsRepo(app.coursesRepo)

        //observe - это наблюдатель
        // подписываемся на inProgressLiveData
        viewModel.inProgressLiveData.observe(viewLifecycleOwner) { inProgress ->
            //сюда приходит значение
            lessonsRecyclerView.isVisible = !inProgress
            progressBar.isVisible = inProgress
        }

        viewModel.coursesLiveData.observe(viewLifecycleOwner) {
            adapter.setData(listOf())// пополнение адаптера данными
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

    fun setCourse(lesson: CourseEntity) {
        adapter.setData(lesson.lessons)// пополнение адаптера данными
    }

    fun openLesson(lessonEntity: LessonEntity) {
        getController().openLesson(lessonEntity)
    }
}