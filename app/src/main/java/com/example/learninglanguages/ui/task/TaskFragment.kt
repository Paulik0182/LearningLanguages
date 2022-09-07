package com.example.learninglanguages.ui.task

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.App
import com.example.learninglanguages.Key
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.ui.task.answer.AnswerAdapter
import com.squareup.picasso.Picasso

internal const val DEFAULT_COURSE_ID_KEY = -1L
internal const val DEFAULT_LESSON_ID_KEY = -1L

class TaskFragment : Fragment(R.layout.fragment_task_v2) {

    private val app: App by lazy { requireActivity().application as App }

    private lateinit var taskTv: TextView
    private lateinit var taskImageView: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var linerLayout: LinearLayout

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnswerAdapter

    private val viewModel: TaskViewModel by viewModels {
        TaskViewModel.Factory(app.coursesRepo, courseId, lessonId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        val lessonEntity: LessonEntity = arguments?.get(Key.THEME_ID_ARGS_KEY) as LessonEntity
        taskList = ArrayList(lessonEntity.tasks)//создали копию всех элементов

        courseId = arguments?.getLong(Key.THEME_ID_ARGS_KEY) ?: DEFAULT_COURSE_ID_KEY
        lessonId = arguments?.getLong(Key.TASK_ID_ARGS_KEY) ?: DEFAULT_LESSON_ID_KEY

        //observe - это наблюдатель
        // подписываемся на inProgressLiveData
        viewModel.inProgressLiveData.observe(viewLifecycleOwner) { inProgress ->
            //сюда приходит значение
            recyclerView.isVisible = !inProgress
            progressBar.isVisible = inProgress
        }

        viewModel.tasksLiveData.observe(viewLifecycleOwner) { task ->
            taskTv.text = task.task

            Picasso.get().load(task.taskImageUrl).into(taskImageView)
            taskImageView.scaleType = ImageView.ScaleType.FIT_XY

            task?.let {
                adapter.setData(it.variantsAnswer)
                adapter.setOnItemClickListener { right ->
                    answer = checkingAnswer(it.rightAnswer, right)
                }
            }
        }

        viewModel.selectedSuccessLiveData.observe(viewLifecycleOwner) {
            getController().openSuccessScreen()
        }
    }

    private fun checkingAnswer(rightAnswer: String, right: String): Boolean {
        return rightAnswer == right
    }

    private fun initView(view: View) {
        taskTv = view.findViewById(R.id.task_text_view)
        taskImageView = view.findViewById(R.id.task_image_view)
        progressBar = view.findViewById(R.id.progress_task_bar)
        linerLayout = view.findViewById(R.id.task_liner_layout)

        recyclerView = view.findViewById(R.id.task_answer_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = AnswerAdapter()
        recyclerView.adapter = adapter
    }

    interface Controller {
        fun openSuccessScreen()
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {
        @JvmStatic
        fun newInstance(courseId: Long, lessonId: Long) = TaskFragment().apply {
            arguments = Bundle().apply {
                putLong(Key.THEME_ID_ARGS_KEY, courseId)
                putLong(Key.TASK_ID_ARGS_KEY, lessonId)
            }
        }
    }

//private var notice: String = showNotice("Key.SHOW_NOTICE_TASK_FRAGMENT_KEY")
//
//    private fun showNotice(notice: String) {
//        Toast.makeText(context, notice, Toast.LENGTH_SHORT).show()
//    }
}