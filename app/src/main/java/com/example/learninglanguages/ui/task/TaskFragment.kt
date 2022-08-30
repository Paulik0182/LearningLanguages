package com.example.learninglanguages.ui.task

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.Key
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.entities.TaskEntity
import com.example.learninglanguages.ui.task.answer.AnswerAdapter
import com.example.learninglanguages.ui.task.answer.TasksViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


class TaskFragment : Fragment(R.layout.fragment_task_v2) {

    private lateinit var taskTv: TextView
    private lateinit var taskImageView: ImageView

    private lateinit var taskList: MutableList<TaskEntity>//кэшируем сущность

    private lateinit var adapter: AnswerAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var linearLayout: LinearLayout

    private lateinit var recyclerView: RecyclerView

    private val viewModel: TasksViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        val lessonEntity: LessonEntity = arguments?.get(Key.THEME_ARGS_KEY) as LessonEntity
        //enum передать нельзя это полноценный объект. Поэтому кладем порядковый номер и потом его достаем

        //тест. Свалить приложение
//        throw RuntimeException("Crash!")

        taskList =
            ArrayList(lessonEntity.tasks)//создали копию всех элементов. Так как мы удаляем по одному заданию делаем так

        // если придет null обработается исключение, выполнится код после ?:
        fillView(getNextTask() ?: throw IllegalArgumentException("Список заданий пуст"))

        viewModel.inProgressLiveData.observe(viewLifecycleOwner) { inProgress ->
            //сюда приходит значение
            recyclerView.isVisible = !inProgress
            progressBar.isVisible = inProgress
        }

        viewModel.tasksLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it.variantsAnswer)
        }

        viewModel.selectedSuccessLiveData.observe(viewLifecycleOwner) {
            getController().openSuccessScreen()
        }
    }

    //заполняем данными
    private fun fillView(taskEntity: TaskEntity) {

        taskTv.text = taskEntity.task
        //работа с картинками
        Picasso.get().load(taskEntity.taskImageUrl).into(taskImageView)
        taskImageView.scaleType = ImageView.ScaleType.FIT_XY// растягиваем картинку на весь элемент

        adapter.setData(taskEntity.variantsAnswer)
        adapter.setOnItemClickListener {
            handleAnswerClick(taskEntity.rightAnswer, it)// передали нажатие на кнопку
        }
    }

    // обработка нажатия на кнопку
    private fun handleAnswerClick(rightAnswer: String, selectedAnswer: String) {

        val isCorrect = checkingAnswer(rightAnswer, selectedAnswer)

        if (isCorrect) {
            val taskEntity = getNextTask()
            if (taskEntity == null) {//обязательно должна быть обработка null
                finishLesson()
            } else {
                fillView(taskEntity)
            }
        } else {
            Toast.makeText(
                requireContext(),
                "Вы ошиблись, попробуйте еще раз!!!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    //рандомный метод получающий список (новый список)
    private fun getNextTask(): TaskEntity? {
        val nextTask =
            taskList.randomOrNull()//означает что рандом может принимать null. Знак ? у TaskEntity обязателен
        taskList.remove(nextTask) //удаляем из списка одно отобнанное рандомным способом задание
        return nextTask
    }

    //  это сравнение двух переменных на равенство. Возвращается true & false
    private fun checkingAnswer(rightAnswer: String, selectedAnswer: String): Boolean {
        return rightAnswer == selectedAnswer
    }

    private fun finishLesson() {
//        Toast.makeText(requireContext(), "УРА!!! Вы выполнили все задания", Toast.LENGTH_SHORT)
//            .show()
        getController().openSuccessScreen()
    }

    private fun getController(): Controller = activity as Controller

//    private fun getController(): Controller = activity as? Controller
//        ?: throw IllegalStateException("Активити должна наследовать контроллер!!!")//вариант1.  проверки, наследует или нет активити контроллер

    interface Controller {
        fun openSuccessScreen()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()  //Вариант 2. агресивный способ проверки наличия контроллера. Если нет контроллера, приложение свалтится на присоединение к фрагмента к активити
    }

    private fun initViews(view: View) {
        linearLayout = view.findViewById(R.id.task_liner_layout)
        taskTv = view.findViewById(R.id.task_text_view)
        taskImageView = view.findViewById(R.id.task_image_view)
        recyclerView = view.findViewById(R.id.answer_recycler_view)
        progressBar = view.findViewById(R.id.progress_task_bar)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = AnswerAdapter()
        recyclerView.adapter = adapter
    }

    //вариант 4 Более по Kotlin (оптимальный)
    companion object {
        @JvmStatic
        fun newInstance(lessonEntity: LessonEntity) = TaskFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Key.THEME_ARGS_KEY, lessonEntity)
            }
        }
    }
}
