package com.example.learninglanguages.ui

import android.content.Context
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
import com.example.learninglanguages.domain.ThemeTask

class TaskFragment : Fragment() {
    private lateinit var taskTv: TextView
    private lateinit var answerTv1: TextView
    private lateinit var answerTv2: TextView
    private lateinit var answerTv3: TextView
    private lateinit var answerTv4: TextView

    private val app: App by lazy { requireActivity().application as App }

    private lateinit var taskList: MutableList<TaskEntity>//кэшируем сущность

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

        taskList = when (ThemeTask.values()[original]) {
            ThemeTask.ENGLISH -> app.englishTaskRepo
            ThemeTask.KOTLIN -> app.kotlinTaskRepo
        }.getTasks() as MutableList //инициализировали taskList, то-есть получили себе репо (все данные)


        // если придет null обработается исключение, выполнится код после ?:
        fillView(getNextTask() ?: throw IllegalArgumentException("Список заданий пуст"))

    }

    //заполняем данными
    private fun fillView(taskEntity: TaskEntity) {
        taskTv.text = taskEntity.task
        answerTv1.text = taskEntity.variantsAnswer[0]
        answerTv2.text = taskEntity.variantsAnswer[1]
        answerTv3.text = taskEntity.variantsAnswer[2]
        answerTv4.text = taskEntity.variantsAnswer[3]

        answerTv1.setOnClickListener {
            handleAnswerClick(
                taskEntity.rightAnswer,
                taskEntity.variantsAnswer[0]
            )// передали нажатие на кнопку
        }

        answerTv2.setOnClickListener {
            handleAnswerClick(taskEntity.rightAnswer, taskEntity.variantsAnswer[1])
        }

        answerTv3.setOnClickListener {
            handleAnswerClick(taskEntity.rightAnswer, taskEntity.variantsAnswer[2])
        }

        answerTv4.setOnClickListener {
            handleAnswerClick(taskEntity.rightAnswer, taskEntity.variantsAnswer[3])
        }
    }

    // обработка нажатия на кнопку
    private fun handleAnswerClick(rightAnswer: String, selectedAnswer: String) {
        //верный ответ textView
//        val rightAnswerTv = when (answerNum) {
//            answerNum -> answerTv1
//            answerNum -> answerTv2
//            answerNum -> answerTv3
//            answerNum -> answerTv4
//            else -> null
//        }
        val isCorrect = checkingAnswer(rightAnswer, selectedAnswer)

        if (isCorrect) {
//            rightAnswerTv?.setBackgroundColor(Color.GREEN)
            val taskEntity = getNextTask()
            if (taskEntity == null) {//обязательно должна быть обработка null
                finishLesson()
            } else {
                fillView(taskEntity)
            }
        } else {
//            rightAnswerTv?.setBackgroundColor(Color.BLACK)
            Toast.makeText(
                requireContext(),
                "Вы ошиблись, попробуйте еще раз!!!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun finishLesson() {
        //todo
        Toast.makeText(requireContext(), "УРА!!! Вы выполнили все задания", Toast.LENGTH_SHORT)
            .show()
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
