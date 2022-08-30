package com.example.learninglanguages.ui.courses

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.App
import com.example.learninglanguages.R
import com.example.learninglanguages.dagger.AppComponent
import com.example.learninglanguages.domain.entities.CourseEntity
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.repos.CoursesRepo
import javax.inject.Inject

class CoursesFragment : Fragment(R.layout.fragment_courses) {

    @Inject
    lateinit var coursesRepo: CoursesRepo

    private val app: App by lazy { requireActivity().application as App }
    private val viewModel: CoursesViewModel by viewModels {
        CoursesViewModel.Factory(app.coursesRepo)
    }
//    private val viewModel: CoursesViewModel by viewModelsFactory{ExampleViewModel(requireActivity().getString(
//    Key.KEY_VIEW_MODEL_ID))}
//    private val _viewModel: ViewModel = ViewModelProviders.of(this).get(CoursesViewModel::class.java)
    //в связи с тем что презентер при каждом повороте пересоздается, а это если необходимо сохранять экран, необходимо презентор сохранить вне данного класса

    //этот метод достает из MAP или создает новый презентер
//    private fun extractViewModel(): CoursesViewModel {
//        val presenter = app.rotationFreeStorage[fragmentUid] as CoursesViewModel?
//            ?: CoursesViewModel(coursesRepo)
//        app.rotationFreeStorage[fragmentUid] = presenter
//        return presenter
//    }

    private lateinit var adapter: CoursesAdapter

    private lateinit var coursesRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private lateinit var appComponent: AppComponent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        appComponent.inject(this)

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