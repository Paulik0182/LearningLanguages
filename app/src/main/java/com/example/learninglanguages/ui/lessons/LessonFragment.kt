package com.example.learninglanguages.ui.lessons

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.App
import com.example.learninglanguages.Key
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.CoursesRepo
import java.util.*

class LessonFragment : Fragment(), LessonsContract.View {

    private val app: App by lazy { requireActivity().application as App }
    private lateinit var adapter: LessonsAdapter

    private val presenter: LessonsContract.Presenter by lazy { extractPresenter() }//поздняя инициализация презентора, положили в него repo
    //в связи с тем что презентер при каждом повороте пересоздается, а это если необходимо сохранять экран, необходимо презентор сохранить вне данного класса

    //этот метод достает из MAP или создает новый презентер
    private fun extractPresenter(): LessonsContract.Presenter {
//        val courseId = arguments?.getLong(Key.COURSE_ID_ARGS_KEY)
//        requireNotNull(arguments?.getLong(Key.COURSE_ID_ARGS_KEY))
        val presenter = app.rotationLessonFreeStorage[fragmentUid] as LessonsContract.Presenter?
            ?: LessonsPresenter(
                coursesRepo,
                requireNotNull(arguments?.getLong(Key.COURSE_ID_ARGS_KEY))
            )
        app.rotationLessonFreeStorage[fragmentUid] = presenter
        return presenter
    }

    private lateinit var lessonsRecyclerView: RecyclerView
    private val coursesRepo: CoursesRepo by lazy {
        app.coursesRepo
    }

    //уникальный id (для того чтобы можно было сохранить состояние экрана за пределами класса
    private lateinit var fragmentUid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentUid =
            savedInstanceState?.getString(Key.FRAGMENT_LESSON_UUID_KEY) ?: UUID.randomUUID()
                .toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //при сохроанении положить ID
        outState.putString(Key.FRAGMENT_LESSON_UUID_KEY, fragmentUid)
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

        Toast.makeText(context, fragmentUid, Toast.LENGTH_SHORT).show()
    }

    //отсоединили view
    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
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
    }

    override fun openLesson(lessonEntity: LessonEntity) {
        getController().openLesson(lessonEntity)
    }
}