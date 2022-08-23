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
import com.example.learninglanguages.Key
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.CoursesRepo
import java.util.*

class CoursesFragment : Fragment(), CoursesContract.View {

    private val app: App by lazy { requireActivity().application as App }
    private val presenter: CoursesContract.Presenter by lazy { extractPresenter() }//поздняя инициализация презентора, положили в него repo
    //в связи с тем что презентер при каждом повороте пересоздается, а это если необходимо сохранять экран, необходимо презентор сохранить вне данного класса

    //этот метод достает из MAP или создает новый презентер
    private fun extractPresenter(): CoursesContract.Presenter {
        val presenter = app.rotationFreeStorage[fragmentUid] as CoursesContract.Presenter?
            ?: CoursesPresenter(coursesRepo)
        app.rotationFreeStorage[fragmentUid] = presenter
        return presenter
    }

    private lateinit var adapter: CoursesAdapter

    private lateinit var coursesRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val coursesRepo: CoursesRepo by lazy {
        app.coursesRepo
    }

    //уникальный id (для того чтобы можно было сохранить состояние экрана за пределами класса
    private lateinit var fragmentUid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //проверка, есть ли это значение, если нет то создаем его
        fragmentUid =
            savedInstanceState?.getString(Key.FRAGMENT_UUID_KEY) ?: UUID.randomUUID().toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //при сохроанении положить ID
        outState.putString(Key.FRAGMENT_UUID_KEY, fragmentUid)
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

        presenter.attach(this)//в призентаре вызываем функцию attach и передаем себя
    }

    //отсоединили view
    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
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

    override fun setCourses(course: MutableList<CourseEntity>) {
        adapter.setData(course)// пополнение адаптера данными
    }

    override fun openLesson(lessonEntity: LessonEntity) {
        getController().openLesson(lessonEntity)
    }

    override fun openCourse(courseEntity: CourseEntity) {
        getController().openCourse(courseEntity)
    }
}