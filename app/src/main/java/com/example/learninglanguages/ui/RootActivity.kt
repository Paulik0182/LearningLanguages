package com.example.learninglanguages.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.learninglanguages.Key
import com.example.learninglanguages.R
import com.example.learninglanguages.domain.entities.LessonEntity
import com.example.learninglanguages.ui.lessons.CoursesFragment
import com.example.learninglanguages.ui.task.TaskFragment

class RootActivity : AppCompatActivity(),
    TaskFragment.Controller,
    SuccessFragment.Controller,
    CoursesFragment.Controller {

    private val defaultTitle: String by lazy { getString(R.string.app_name) }

    var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        title = defaultTitle

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_layout, CoursesFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favourites_menu_items -> {
                Toast.makeText(this, "Избраное", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.statistics_menu_item -> {
                Toast.makeText(this, "Статистика", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.settings_menu_item -> {
                Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.about_app_menu_item -> {
                Toast.makeText(this, "О приложении", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openTaskFragment(lessonEntity: LessonEntity) {
        //читается когда (when) ThemeTask это ThemeTask.ENGLISH тогда запустить этот фрагмент
//        val fragment: Fragment = when (themeTask) {
//            ThemeTask.KOTLIN -> TaskFragment()
//            ThemeTask.ENGLISH -> TaskFragment()
//        }

        val fragment: Fragment = TaskFragment.newInstance(lessonEntity)
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
            title = defaultTitle
        }
    }

    override fun onBackPressed() {
        exitingApplicationDoubleClick()
    }

    //выход из приложения по двойному нажатию на кнопку
    private fun exitingApplicationDoubleClick() {
        if (System.currentTimeMillis() - backPressedTime <= 3_000) {
            super.onBackPressed()
            title = defaultTitle
            backPressedTime = 0//обнуляем время если вышли из фрагмента
        } else {
            Toast.makeText(
                this,
                "Нажмите еще раз, чтобы выйти из приложения", Toast.LENGTH_LONG
            )
                .show()
        }
        backPressedTime = System.currentTimeMillis()
        finishSuccessFragment()
    }

    override fun openLesson(lessonEntity: LessonEntity) {
        openTaskFragment(lessonEntity)
        title = lessonEntity.name
    }
}