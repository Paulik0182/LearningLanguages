package com.example.learninglanguages.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.learninglanguages.App
import com.example.learninglanguages.Key
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.TaskEntity
import com.example.learninglanguages.domain.TaskRepo
import com.example.learninglanguages.domain.ThemeTask

class TaskFragment : Fragment() {
    private lateinit var taskTv: TextView
    private lateinit var answerTv1: TextView
    private lateinit var answerTv2: TextView
    private lateinit var answerTv3: TextView
    private lateinit var answerTv4: TextView

    private val app: App by lazy { requireActivity().application as App }

    private lateinit var taskRepo: TaskRepo//экземпляр класса, для того чтобы получить данные
    private lateinit var taskList: MutableList<TaskEntity>//кэшируем сущность
    private lateinit var taskEntity: TaskEntity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        val original = arguments?.getInt(Key.THEME_ARGS_KEY) ?: 0
        //enum передать нельзя это полноценный объект. Поэтому кладем порядковый номер и потом его достаем

        taskRepo = when (ThemeTask.values()[original]) {
            ThemeTask.ENGLISH -> app.englishTaskRepo
            ThemeTask.KOTLIN -> app.kotlinTaskRepo
        }

        taskList =
            taskRepo.getTasks() as MutableList //инициализировали taskList, то-есть получили себе репо (все данные)
        taskEntity = getNextTask()

        fillView(taskEntity)

    }

    //заполняем данными
    private fun fillView(taskEntity: TaskEntity) {
        taskTv.text = taskEntity.task
        answerTv1.text = taskEntity.variantsAnswer[0]
        answerTv2.text = taskEntity.variantsAnswer[1]
        answerTv3.text = taskEntity.variantsAnswer[2]
        answerTv4.text = taskEntity.variantsAnswer[3]

        answerTv1.setOnClickListener {
            checkingAnswer(taskEntity.variantsAnswer[0])// передали нажатие на кнопку
        }

        answerTv2.setOnClickListener {
            checkingAnswer(taskEntity.variantsAnswer[1])
        }

        answerTv3.setOnClickListener {
            checkingAnswer(taskEntity.variantsAnswer[2])
        }

        answerTv4.setOnClickListener {
            checkingAnswer(taskEntity.variantsAnswer[3])
        }

    }

    //рандомный метод получающий список (новый список)
    private fun getNextTask(): TaskEntity = taskRepo.getTasks().random()

    // selectedAnswer - это приходит нажатие на кнопку
    private fun checkingAnswer(selectedAnswer: String) {

        val rightAnswer = taskEntity.rightAnswer// правильный ответ

        //верный ответ textView
//        val rightAnswerTv = when (answerNum) {
//            answerNum -> answerTv1
//            answerNum -> answerTv2
//            answerNum -> answerTv3
//            answerNum -> answerTv4
//            else -> null
//        }

        if (rightAnswer == selectedAnswer) {
//            rightAnswerTv?.setBackgroundColor(Color.GREEN)
            taskEntity = getNextTask()
            fillView(taskEntity)
        } else {
//            rightAnswerTv?.setBackgroundColor(Color.BLACK)
            Toast.makeText(context, "Вы ошиблись, попробуйте еще раз!!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews(view: View) {

        taskTv = view.findViewById(R.id.task_text_view)
        answerTv1 = view.findViewById(R.id.answer1_text_view)
        answerTv2 = view.findViewById(R.id.answer2_text_view)
        answerTv3 = view.findViewById(R.id.answer3_text_view)
        answerTv4 = view.findViewById(R.id.answer4_text_view)

    }

    //вариант 4 Более по Kotlin (оптимальный)
    companion object {
        @JvmStatic
        fun newInstance(themeTask: ThemeTask) = TaskFragment().apply {
            arguments = Bundle().apply {
                putInt(Key.THEME_ARGS_KEY, themeTask.ordinal)
            }
        }
    }

    //вариант 1
//    companion object{
//        @JvmStatic
//        fun newInstance(themeTask: ThemeTask): Fragment{
//            val args = Bundle()
//            args.putInt(Key.THEME_ARGS_KEY, themeTask.ordinal)
//            val fragment = TaskFragment()
//            fragment.arguments = args
//            return fragment
//        }
//    }

    //вариант 2 Более по Kotlin
//    companion object {
//        @JvmStatic
//        fun newInstance(themeTask: ThemeTask): Fragment {
//            return TaskFragment().apply {
//                val args = Bundle().apply {
//                    putInt(Key.THEME_ARGS_KEY, themeTask.ordinal)
//                }
//                arguments = args
//            }
//        }
//    }

    //вариант 3 Более по Kotlin
//    companion object {
//        @JvmStatic
//        fun newInstance(themeTask: ThemeTask): Fragment {
//            return TaskFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(Key.THEME_ARGS_KEY, themeTask.ordinal)
//                }
//            }
//        }
//    }

}
