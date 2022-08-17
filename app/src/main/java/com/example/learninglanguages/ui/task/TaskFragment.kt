package com.example.learninglanguages.ui.task

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learninglanguages.Key
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.domain.entities.TaskEntity
import com.example.learninglanguages.ui.task.answer.AnswerAdapter
import com.example.learninglanguages.ui.task.answer.TasksContract
import com.squareup.picasso.Picasso

class TaskFragment : Fragment(), TasksContract.View {
    private lateinit var taskTv: TextView
    private lateinit var taskImageView: ImageView

    private lateinit var taskList: MutableList<TaskEntity>//кэшируем сущность

    private lateinit var adapter: AnswerAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var linearLayout: LinearLayout

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_v2, container, false)
    }

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
    }

    //заполняем данными
    private fun fillView(taskEntity: TaskEntity) {

        showProgress(true)

        taskTv.text = taskEntity.task
        //работа с картинками
        Picasso.get().load(taskEntity.taskImageUrl).into(taskImageView)
        taskImageView.scaleType = ImageView.ScaleType.FIT_XY// растягиваем картинку на весь элемент

        showProgress(false)

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

    private fun finishLesson() {
//        Toast.makeText(requireContext(), "УРА!!! Вы выполнили все задания", Toast.LENGTH_SHORT)
//            .show()
        getController().showSuccessScreen()
    }

    private fun getController(): Controller = activity as Controller

//    private fun getController(): Controller = activity as? Controller
//        ?: throw IllegalStateException("Активити должна наследовать контроллер!!!")//вариант1.  проверки, наследует или нет активити контроллер

    interface Controller {
        fun showSuccessScreen()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()  //Вариант 2. агресивный способ проверки наличия контроллера. Если нет контроллера, приложение свалтится на присоединение к фрагмента к активити
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

    override fun showProgress(inProgress: Boolean) {
        if (inProgress) {
            linearLayout.visibility = View.GONE //скрываем view со списком
            progressBar.visibility = View.VISIBLE //показываем прогресс загрузки
        } else {
            linearLayout.visibility = View.VISIBLE //показываем view со списком
            progressBar.visibility = View.GONE //скрываем прогресс загрузки
        }
    }

    override fun setCourses(tasks: List<TaskEntity>) {
        TODO("Not yet implemented")
    }
}
