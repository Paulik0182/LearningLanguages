package com.example.learninglanguages.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.learninglanguages.Key
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.ThemeTask
import com.example.learninglanguages.ui.lessons.LessonsFragment
import com.example.learninglanguages.ui.task.TaskFragment

class RootActivity : AppCompatActivity(),
    TaskFragment.Controller,
    SuccessFragment.Controller,
    LessonsFragment.Controller {

    private val defaultTitle: String by lazy { getString(R.string.app_name) }

    var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        title = defaultTitle

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_layout, LessonsFragment())
            .commit()
    }

    private fun openTaskFragment(themeTask: ThemeTask) {
        //читается когда (when) ThemeTask это ThemeTask.ENGLISH тогда запустить этот фрагмент
//        val fragment: Fragment = when (themeTask) {
//            ThemeTask.KOTLIN -> TaskFragment()
//            ThemeTask.ENGLISH -> TaskFragment()
//        }

        val fragment: Fragment = TaskFragment.newInstance(themeTask)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_layout, fragment, Key.TEG_TASK_CONTAINER_KEY)
            .addToBackStack(null)
            .commit()
    }

    //открываем фрагмент при завершении заданий
    override fun showSuccessScreen() {
        supportFragmentManager.popBackStack()//чистит стэк, после появления данного фрагмента нельзя будет вернутся

        val successFragment: Fragment = SuccessFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_layout, successFragment, Key.TEG_SUCCESS_CONTAINER_KEY)
            .commit()
    }

    override fun finishSuccessFragment() {
        supportFragmentManager.findFragmentByTag(Key.TEG_SUCCESS_CONTAINER_KEY)?.let {
            supportFragmentManager
                .beginTransaction()
                .remove(it)
                .commit()
        }
    }

    override fun openLesson(themeTask: ThemeTask, stringResId: Int) {
        openTaskFragment(themeTask)
        setTitle(stringResId)
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        title = defaultTitle
        exitingApplicationDoubleClick()
    }

    //выход из приложения по двойному нажатию на кнопку
    private fun exitingApplicationDoubleClick() {
        if (backPressedTime + 3000 >= System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, "Нажмите еще раз, чтобы выйти из приложения", Toast.LENGTH_LONG)
                .show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}